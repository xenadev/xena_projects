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
	private final String TAG = BooksDataSource.class.getSimpleName();
	private SQLiteDatabase database;
	private BookDbHelper dbHelper;
	private String[] allColumns = { BookDbHelper.NAME, BookDbHelper.AUTHOR,
			BookDbHelper.EVARIANT, BookDbHelper.GENRE,
			BookDbHelper.PUBLISH_DATE };

	public BooksDataSource(Context context) {
		dbHelper = new BookDbHelper(context);
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

	private Book cursorToBook(Cursor cursor) {
		Book book = new Book();
		book.setName(cursor.getString(0));
		book.setAuthor(cursor.getString(1));
		book.setEVariantPresent((cursor.getInt(2) == 1) ? true : false);
		book.setGenre(cursor.getString(3));
		book.setPublishDate(BookListUtils.convertStringToDate(cursor
				.getString(4)));
		return book;
	}

}
