package com.jim.util.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * provide a util to convert date/dateTime to String
 * 
 * @author jim.huang
 *
 */
public final class DateUtil {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private DateUtil() {}
	
	
	/**
	 * 
	 * @return a string like '2012-01-12', without zone
	 */
	public static String today() {
		final LocalDate localDate = LocalDate.now();
		return localDate.toString();
	}
	
	
	/**
	 * 
	 * @return a string like '2012-01-12 11:10:10', 24h format without zone
	 */
	public static String now() {
		final LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.format(DATE_TIME_FORMATTER);
	}
	
	/**
	 * without zone
	 * @param dateTime format like '2012-01-12 11:10:10' or "2012-01-12T11:10:10"
	 * @return date format like '2012-01-12'
	 */
	public static String dateTime2Date(final String dateTime) {
		LocalDateTime localDateTime = null;
		try {
			localDateTime = LocalDateTime.parse(dateTime);
		} catch (final Exception e) {
			localDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
		}
		final LocalDate localDate = localDateTime.toLocalDate();
		return localDate.toString();
	}
	
	/**
	 * 
	 * @param date "2012-01-12", allow dateTime format
	 * @return dateTime with min time, like "2012-01-12 00:00:00"
	 */
	public static String getMinDateTime(final String date) {
		LocalDate localDate = null;
		try {
			localDate = LocalDate.parse(date);
		} catch (final Exception e) {
			localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
		}
		final LocalTime localTime = LocalTime.MIN;
		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		return localDateTime.format(DATE_TIME_FORMATTER);
	}
	
	
	/**
	 * 
	 * @param date "2012-01-12", allow dateTime format
	 * @return ateTime with max time, like "2012-01-12 23:59:59"
	 */
	
	public static String getMaxDateTime(final String date) {
		LocalDate localDate = null;
		try {
			localDate = LocalDate.parse(date);
		} catch (final Exception e) {
			localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
		}
		final LocalTime localTime = LocalTime.MAX;
		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		return localDateTime.format(DATE_TIME_FORMATTER);
	}
	
	
}

