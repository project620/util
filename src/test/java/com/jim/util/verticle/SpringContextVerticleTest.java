package com.jim.util.verticle;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * 
 * @author jim.huang
 *
 */
@RunWith(VertxUnitRunner.class)
public class SpringContextVerticleTest {

	
	@Test
	public void testDeploySpringVerticle(final TestContext context) {
		final Vertx vertx = Vertx.vertx();
		final Verticle verticle = new Verticle2();
		vertx.deployVerticle(new Verticle2(), context.asyncAssertSuccess(rs -> {
			vertx.deployVerticle(new Verticle2(), context.asyncAssertSuccess());
		}));
		
	}
	
	
	
	public static void main(final String[] args) {
		final Vertx vertx = Vertx.vertx();
		final Verticle verticle = new Verticle2();
		vertx.deployVerticle(verticle, rs -> {
			vertx.deployVerticle(verticle, rs2 -> {
				vertx.eventBus().publish("v2", "jim");
			});
		});
	}
}
