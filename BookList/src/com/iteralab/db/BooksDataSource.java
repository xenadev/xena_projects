package com.iteralab.db;

import java.util.ArrayList;
import java.util.List;

import com.iteralab.booklist.Book;
import com.iteralab.booklist.BookListUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BooksDataSource {
	private static final String TAG = BooksDataSource.class.getSimpleName();
	private SQLiteDatabase database;
	private BookDbHelper dbHelper;
	private String[] allColumns = { BookDbHelper.BOOK_ID, BookDbHelper.NAME,
			BookDbHelper.AUTHOR, BookDbHelper.EVARIANT, BookDbHelper.GENRE,
			BookDbHelper.PUBLISH_DATE };

	private static BooksDataSource instance;

	private BooksDataSource(Context context) {
		dbHelper = new BookDbHelper(context);
	}

	public static BooksDataSource getInstance(Context context) {
		if (instance == null) {
			instance = new BooksDataSource(context);
			Log.i(TAG, "New data source created.");
		} else {
			Log.i(TAG, "Data source already exists.");
		}

		return instance;
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();

		Cursor cursor = database.query(BookDbHelper.TABLE_NAME, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Book book = cursorToBook(cursor);
			books.add(book);
			cursor.moveToNext();
		}
		cursor.close();
		return books;
	}

	public int deleteBook(Book book) {
		int id = book.getBookId();
		int rowsAffected = database.delete(BookDbHelper.TABLE_NAME,
				BookDbHelper.BOOK_ID + " = " + id, null);
		return rowsAffected;
	}

	private Book cursorToBook(Cursor cursor) {
		Book book = new Book();
		book.setBookId(cursor.getInt(0));
		book.setName(cursor.getString(1));
		book.setAuthor(cursor.getString(2));
		book.setEVariantPresent((cursor.getInt(3) == 1) ? true : false);
		book.setGenre(cursor.getString(4));
		book.setPublishDate(BookListUtils.convertStringToDate(cursor
				.getString(5)));
		return book;
	}

}
