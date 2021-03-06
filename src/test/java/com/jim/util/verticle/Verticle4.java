package com.jim.util.verticle;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

@Component
@Deploy(order = 4)
public class Verticle4 extends AbstractVerticle {

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		System.out.println("start " + this.deploymentID());
		startFuture.complete();
	}

	@Override
	public void stop(final Future<Void> stopFuture) throws Exception {
		System.out.println("stop " + this.deploymentID());
		stopFuture.complete();
	}

	
}
