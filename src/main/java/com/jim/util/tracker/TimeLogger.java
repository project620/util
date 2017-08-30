package com.jim.util.tracker;
/**
 * 
 * @author jim.huang
 *
 */
public class TimeLogger {

	
	/**
	 * 
	 * @param taskName
	 * @param timeTracker
	 */
	public static void runTimeTracker(final String taskName, final TimeTracker timeTracker) {
		final long start = System.currentTimeMillis();
		try {
			timeTracker.run();
		} finally {
			final long end = System.currentTimeMillis();
			final long cost = end - start;
			System.out.println("task [ " + taskName + " ] cost : " + cost + " (ms)");
		}
	}
	
}
