package com.jim.util.logger;

import org.slf4j.LoggerFactory;

/**
 * 
 * @author jim.huang
 *
 * slf4j logger wrapper , 
 * in most case, we need to log  debug level log for an entity, and if the entity is too large, then it waste lot of resource
 */
public class UtilLogger{

	private final org.slf4j.Logger slf4jLogger;
	
	
	public UtilLogger(final String name) {
		slf4jLogger = LoggerFactory.getLogger(name);
	}
	
	public UtilLogger(final Class<?> clazz) {
		slf4jLogger = LoggerFactory.getLogger(clazz);
	}
	
	public UtilLogger(final org.slf4j.Logger logger) {
		if (logger == null) {
			throw new NullPointerException("slf4j logger should not be null when execute " + UtilLogger.class.getName() + " constructor");
		}
		slf4jLogger = logger;
	}

	public void debug(final String desc, final Object entity) {
		if (slf4jLogger.isDebugEnabled()) {
			slf4jLogger.debug(desc, entity.toString());
		}
	}
}
