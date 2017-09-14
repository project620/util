package com.jim.util.verticle;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

@Component
@Deploy(order = 2)
public class Verticle2 extends AbstractVerticle {

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		startFuture.complete();
	}
}
