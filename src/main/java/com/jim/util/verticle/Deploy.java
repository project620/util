package com.jim.util.verticle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Deploy {
	
	/**
	 * the order of the verticle that you want to deploy, from small to large.
	 * means 1 will be first deployed, then 2
	 * @return
	 */
	int order() default 1;
	
}
