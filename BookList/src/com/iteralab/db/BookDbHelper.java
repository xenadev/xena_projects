package com.iteralab.db;

import com.iteralab.booklist.BookGenre;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class BookDbHelper extends SQLiteOpenHelper implements BaseColumns {
	private final String TAG = BookDbHelper.class.getSimpleName();
	public static final String DB_BOOKS = "books.db";

	public static final String TABLE_NAME = "books";
	public static final String BOOK_ID="_id";
	public static final String NAME = "name";
	public static final String AUTHOR = "author";
	public static final String EVARIANT = "e_variant";
	public static final String GENRE = "genre";
	public static final String PUBLISH_DATE = "publish_date";
	public static final int DB_VERSION = 7;

	private static final String CREATE_TABLE_QUERY = "CREATE TABLE "
			+ TABLE_NAME + " ("+BOOK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
			+ " TEXT, " + AUTHOR + " TEXT, " + EVARIANT + " INTEGER, " + GENRE
			+ " TEXT, " + PUBLISH_DATE + " TEXT);";

	private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	public BookDbHelper(Context context) {
		super(context, DB_BOOKS, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_QUERY);

		ContentValues values = new ContentValues();

		values.put(NAME, "Pro Git");
		values.put(AUTHOR, "Scott Chacon");
		values.put(EVARIANT, 1);
		values.put(GENRE, "Study");
		values.put(PUBLISH_DATE, "02.09.2008");

		db.insert(TABLE_NAME, NAME, values);

		values.put(NAME, "Spring Security 3");
		values.put(AUTHOR, "Peter Mularien");
		values.put(EVARIANT, 0);
		values.put(GENRE, "Study");
		values.put(PUBLISH_DATE, "11.10.2011");

		db.insert(TABLE_NAME, NAME, values);

		values.put(NAME, "Android Forensics");
		values.put(AUTHOR, "Anrew Hoog");
		values.put(EVARIANT, 1);
		values.put(GENRE, "Study");
		values.put(PUBLISH_DATE, "23.02.2011");

		db.insert(TABLE_NAME, NAME, values);

		values.put(NAME, "97 Things Every programmer should know know know know know");
		values.put(AUTHOR, "Nill Ford");
		values.put(EVARIANT, 0);
		values.put(GENRE, BookGenre.STUDY.getValue());
		values.put(PUBLISH_DATE, "29.11.2003");

		db.insert(TABLE_NAME, NAME, values);

		values.put(NAME, "Java database connectivity");
		values.put(AUTHOR, "John Donahue");
		values.put(EVARIANT, 1);
		values.put(GENRE, BookGenre.SCIENCE.getValue());
		values.put(PUBLISH_DATE, "31.08.2011");

		db.insert(TABLE_NAME, NAME, values);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_QUERY);
		onCreate(db);

	}

}
