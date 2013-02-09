package com.iteralab.booklist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class BookEditActivity extends Activity {
	private Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_edit);
		
		Intent inputIntent = getIntent();
		Bundle inputBundle = inputIntent.getExtras();

		book = inputBundle.getParcelable("book");
		
		Toast.makeText(this, book.getName(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_book_edit, menu);
		return true;
	}

}
