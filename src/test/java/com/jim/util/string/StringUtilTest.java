package com.jim.util.string;

import org.junit.Assert;
import org.junit.Test;

import com.jim.util.tracker.TimeLogger;

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

		TimeLogger.runTimeTracker("java replaceAll " + time + " times", () -> {
			for (int i = 0; i < time; i++) {
				code.replaceAll(oldStr, newStr);
			}
		});
		
		TimeLogger.runTimeTracker("util replaceAll " + time + " times", () -> {
			for (int i = 0; i < time; i++) {
				StringUtil.replaceAll(code, oldStr, newStr);
			}
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
		
		TimeLogger.runTimeTracker("java replaceFirst " + time + " times", () -> {
			for (int i = 0; i < time; i++) {
				code.replaceFirst(oldStr, newStr);
			}
		});
		
	
		TimeLogger.runTimeTracker("util replaceFirst " + time + " times", () -> {
			for (int i = 0; i < time; i++) {
				StringUtil.replaceFirst(code, oldStr, newStr);
			}
		});
		
	}
	

}
