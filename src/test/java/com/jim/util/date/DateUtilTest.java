package com.jim.util.date;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author jim.huang
 *
 */
public class DateUtilTest {

	
	@Test
	public void testNowStr() {
		final String today = DateUtil.today();
		System.out.println("today = " + today);
		
		final String now = DateUtil.now();
		System.out.println("now = " + now);
		
		final String dateStr = DateUtil.dateTime2Date("2012-12-12 11:10:11");
		Assert.assertEquals("2012-12-12", dateStr);
		
		final String minDateTime = DateUtil.getMinDateTime("2012-10-10");
		Assert.assertEquals("2012-10-10 00:00:00", minDateTime);
		
		
		final String maxDateTime = DateUtil.getMaxDateTime("2012-10-10");
		Assert.assertEquals("2012-10-10 23:59:59", maxDateTime);
		
		
		final String minDateTimeForDateTime = DateUtil.getMinDateTime("2012-12-12 11:10:11");
		Assert.assertEquals("2012-12-12 00:00:00", minDateTimeForDateTime);
		
		
		final String maxDateTimeForDateTime = DateUtil.getMaxDateTime("2012-12-12 11:10:11");
		Assert.assertEquals("2012-12-12 23:59:59", maxDateTimeForDateTime);
		
	}
	
}
