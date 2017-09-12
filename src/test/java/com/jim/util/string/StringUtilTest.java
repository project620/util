package com.jim.util.string;

import java.util.HashMap;

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
	

	@Test
	public void testString() {
		final String a = "Aa";
		final String b = "BB";
		final HashMap<String, Object> map = new HashMap<>();
		map.put(a, "123");
		map.put(b, "12345");
		System.out.println(a.hashCode() + " -- " + b.hashCode());
		
		System.out.println("a == " + map.get(a));
		System.out.println("b == " + map.get(b));
		
		System.out.println(a.equals(b));
	}
	
}
