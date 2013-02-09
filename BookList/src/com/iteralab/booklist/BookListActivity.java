package com.iteralab.booklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.iteralab.db.BooksDataSource;

public class BookListActivity extends Activity {
	private final String TAG = BookListActivity.class.getSimpleName();
	private GridView booksGrid;
	private boolean backPressed = false;
	private BooksDataSource dataSource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i(TAG, "onCreate");

		booksGrid = (GridView) findViewById(R.id.books_grid);
		booksGrid.setOnItemClickListener(bookItemClick);

		dataSource = BooksDataSource.getInstance(getApplicationContext());
	}

	@Override
	public void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
		if (dataSource != null) {
			dataSource.open();
			showBooksGrid();
		}

	}

	/**
	 * Updates books grid.
	 */
	private void showBooksGrid() {
		BookItemAdapter adapter = new BookItemAdapter(
				this.getApplicationContext(), dataSource);
		booksGrid.setAdapter(adapter);

		((BookItemAdapter) booksGrid.getAdapter()).refreshItems();
	}

	private OnItemClickListener bookItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {

			showBookDetails(position);

		}
	};

	private void showBookDetails(int position) {
		Intent detailsActivityIntent = new Intent(this.getApplicationContext(),
				BookDetailsActivity.class);
		Book book = (Book) ((BookItemAdapter) booksGrid.getAdapter())
				.getItem(position);
		detailsActivityIntent.putExtra("book", book);
		startActivity(detailsActivityIntent);

	}

	@Override
	public void onBackPressed() {
		if (backPressed) {
			finish();
		} else {
			backPressed = true;
			Toast.makeText(getApplicationContext(), "Press Back again to quit",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onPause() {
		if (dataSource != null) {
			dataSource.close();
		}
		super.onPause();
	}

}
