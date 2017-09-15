package com.jim.util.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * 
 * @author jim.huang
 *
 */
public class SpringRouterInitializerVerticle extends AbstractVerticle {

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		startFuture.complete();
	}

	@Override
	public void stop(final Future<Void> stopFuture) throws Exception {
		stopFuture.complete();
	}

	
}
