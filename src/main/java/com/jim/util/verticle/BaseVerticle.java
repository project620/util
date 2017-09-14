package com.jim.util.verticle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Verticle;

/**
 * a verticle to run with spring. auto deploy verticle bean
 * 
 * @author jim.huang
 *
 */

@ComponentScan
@Component(BaseVerticle.CURRENT_BEAN_NAME)
public class BaseVerticle extends AbstractVerticle {

	public static ApplicationContext applicationContext;

	public static final int DEFAULT_DEPLOY_ORDER = Integer.MAX_VALUE;

	public static final String CURRENT_BEAN_NAME = "SpringContextVerticle";

	private static final Logger logger = LoggerFactory.getLogger(BaseVerticle.class);

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		applicationContext = new AnnotationConfigApplicationContext(BaseVerticle.class);
		final Collection<List<Verticle>> verticles = getDeployedBean();
		printVerticles(verticles);
		final LinkedList<Future<Void>> deployFuture = new LinkedList<>();
		final Iterator<List<Verticle>> verticleIterator = verticles.iterator();
		while (verticleIterator.hasNext()) {
			final List<Verticle> verticle = verticleIterator.next();
			if (deployFuture.size() == 0) {
				final Future<Void> firstDeployFuture = autoDeploy(verticle);
				deployFuture.addLast(firstDeployFuture);
			} else {
				final Future<Void> preDeployFuture = deployFuture.getLast();
				final Future<Void> currentFuture = Future.future();
				preDeployFuture.setHandler(rs -> {
					autoDeploy(verticle).setHandler(deploy -> currentFuture.complete());
				});
				deployFuture.addLast(currentFuture);
			}
		}
		if (deployFuture.size() != 0) {
			deployFuture.getLast().setHandler(finalFuture -> startFuture.complete());
		} else {
			startFuture.complete();
		}
		
	}

	private Collection<List<Verticle>> getDeployedBean() {
		final Map<Integer, List<Verticle>> verticleMap = new TreeMap<>();
		final String[] beanNames = applicationContext.getBeanNamesForType(AbstractVerticle.class);
		for (final String beanName : beanNames) {
			if (CURRENT_BEAN_NAME.equals(beanName)) {
				continue;
			}
			final Verticle verticle = applicationContext.getBean(beanName, Verticle.class);
			final Deploy deploy = verticle.getClass().getAnnotation(Deploy.class);
			Integer order = DEFAULT_DEPLOY_ORDER;
			if (deploy != null) {
				order = deploy.order();
			}
			verticleMap.putIfAbsent(order, new ArrayList<>());
			verticleMap.get(order).add(verticle);
		}
		return verticleMap.values();
	}


	private void printVerticles(final Collection<List<Verticle>> verticles) {
		verticles.forEach(list -> {
			list.forEach(verticle -> {
				logger.info("spring managed verticle class = " + verticle.getClass().getName());
			});
		});
	}

	@SuppressWarnings("rawtypes")
	private Future<Void> autoDeploy(final List<Verticle> verticles) {
		final Future<Void> future = Future.future();
		final List<Future> deployFutures = new ArrayList<>();
		for (final Verticle verticle : verticles) {
			final Future<Void> deployFuture = Future.future();
			vertx.deployVerticle(verticle, rs -> {
				if (rs.succeeded()) {
					logger.info("deploy verticle {} successfully", verticle.getClass().getName());
					deployFuture.complete();
				} else {
					logger.error(String.format("deploy verticle %1$s failed", verticle.getClass().getName()),
							rs.cause());
					deployFuture.fail(rs.cause());
				}
			});
			deployFutures.add(deployFuture);
		}
		CompositeFuture.join(deployFutures).setHandler(rs -> {
			future.complete();
		});
		return future;
	}

	@Override
	public void stop(final Future<Void> stopFuture) throws Exception {
		stopFuture.complete();
	}

}
