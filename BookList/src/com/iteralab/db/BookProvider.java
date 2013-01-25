package com.iteralab.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class BookProvider extends ContentProvider {

	public static final Uri CONTENT_URI = Uri
			.parse("content://com.iteralab.db.bookprovider/books");
	private SQLiteDatabase db;

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		int retVal = db.delete(BookDbHelper.TABLE_NAME, where, whereArgs);
		getContext().getContentResolver().notifyChange(uri, null);

		return retVal;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri url, ContentValues inValues) {
		ContentValues values = new ContentValues(inValues);
		long rowId = db.insert(BookDbHelper.TABLE_NAME, BookDbHelper.NAME,
				values);

		if (rowId > 0) {
			Uri uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(uri, null);
			return uri;
		} else {
			throw new SQLException("Failed to insert row into " + url);
		}

	}

	@Override
	public boolean onCreate() {
		db = new BookDbHelper(getContext()).getWritableDatabase();
		return (db == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String orderBy;

		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = BookDbHelper.NAME;
		} else {
			orderBy = sortOrder;
		}

		Cursor c = db.query(BookDbHelper.TABLE_NAME, projection, selection,
				selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {

		int retVal = db.update(BookDbHelper.TABLE_NAME, values, where,
				whereArgs);

		getContext().getContentResolver().notifyChange(uri, null);
		return retVal;
	}

}
