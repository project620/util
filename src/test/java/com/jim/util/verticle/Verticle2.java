package com.jim.util.verticle;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

@Component
@Deploy(order = 2)
public class Verticle2 extends AbstractVerticle {

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		System.out.println("start " + this.deploymentID());
		vertx.eventBus().consumer("v2", rs -> {
			System.out.println("v2");
			rs.reply("end");
		});
		startFuture.complete();
	}
	
	
	@Override
	public void stop(final Future<Void> stopFuture) throws Exception {
		System.out.println("stop " + this.deploymentID());
		stopFuture.complete();
	}
}
