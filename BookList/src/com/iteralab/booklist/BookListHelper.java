package com.iteralab.booklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookListHelper {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy", Locale.ENGLISH);

	/**
	 * Retrieves books.
	 * 
	 * @return
	 */
	public static List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("1", "Thinking in Java", "Author1", true, "study",
				convertStringToDate("21.10.2003")));
		books.add(new Book("2", "Android for dummies", "Author2", false,
				"study", convertStringToDate("22.10.1998")));
		books.add(new Book("3", "C# in practice", "Author3", true, "study",
				convertStringToDate("02.08.2008")));

		return books;

	}

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
