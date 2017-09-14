package com.jim.util.verticle;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

@Component
@Deploy(order = 1)
public class Verticle1 extends AbstractVerticle{

	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		startFuture.complete();
	}

}
