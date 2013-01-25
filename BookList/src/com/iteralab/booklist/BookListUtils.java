package com.iteralab.booklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookListUtils {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy", Locale.ENGLISH);
	
	/**
	 * Converts date to its string representation.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToString(Date date) {
		String strDate = null;
		strDate = date.toString();
		strDate = dateFormat.format(date);
		return strDate;
	}

	/**
	 * Converts string representation of date to Date object.
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
