package com.jim.util.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author jim.huang
 *
 */
public class StringUtilTest {

	private static final int time = 100000;

	@Test
	public void testReplaceAll() {
		final String code = "Codeododod";
		final String oldStr = "od";
		final String newStr = "jim";
		final String newStrByJava = code.replaceAll(oldStr, newStr);
		final String newStrByUtil = StringUtil.replaceAll(code, oldStr, newStr);
		Assert.assertEquals(newStrByJava, newStrByUtil);

		runTimeTrackerTask(() -> {
			for (int i = 0; i < time; i++) {
				code.replaceAll(oldStr, newStr);
			}
			return "java replaceAll " + time + " times";
		});



		runTimeTrackerTask(() -> {
			for (int i = 0; i < time; i++) {
				StringUtil.replaceAll(code, oldStr, newStr);
			}
			return "util replaceAll " + time + " times";
		});

	}

	@Test
	public void testReplaceFirst() {
		final String code = "Codeododod";
		final String oldStr = "od";
		final String newStr = "jim";
		final String newStrByJava = code.replaceFirst(oldStr, newStr);
		final String newStrByUtil = StringUtil.replaceFirst(code, oldStr, newStr);
		Assert.assertEquals(newStrByJava, newStrByUtil);
		
		runTimeTrackerTask(() -> {
			for (int i = 0; i < time; i++) {
				code.replaceFirst(oldStr, newStr);
			}
			return "java replaceFirst " + time + " times";
		});



		runTimeTrackerTask(() -> {
			for (int i = 0; i < time; i++) {
				StringUtil.replaceFirst(code, oldStr, newStr);
			}
			return "util replaceFirst " + time + " times";
		});


	}

	private void runTimeTrackerTask(final TimeTracker timeTracker) {
		final long start = System.currentTimeMillis();
		final String taskName = timeTracker.runTask();
		final long end = System.currentTimeMillis();
		final long cost = end - start;
		System.out.println("task [ " + taskName + " ] cost : " + cost);
	}

	private interface TimeTracker {

		/**
		 * 
		 * @return your task name
		 */
		String runTask();

	}

}
