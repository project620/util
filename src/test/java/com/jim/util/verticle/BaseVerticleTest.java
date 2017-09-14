package com.jim.util.verticle;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * 
 * @author jim.huang
 *
 */
@RunWith(VertxUnitRunner.class)
public class BaseVerticleTest {

	
	@Test
	public void testDeploySpringVerticle(final TestContext context) {
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new BaseVerticle(), context.asyncAssertSuccess());
	}
}
