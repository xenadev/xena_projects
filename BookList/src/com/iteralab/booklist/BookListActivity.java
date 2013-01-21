package com.iteralab.booklist;

import com.iteralab.booklist.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class BookListActivity extends Activity {
	private final String TAG = BookListActivity.class.getSimpleName();
	private GridView booksGrid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		booksGrid = (GridView) findViewById(R.id.books_grid);
		showBooksGrid();
		booksGrid.setOnItemClickListener(bookItemClick);

	}

	/**
	 * Updates books grid.
	 */
	private void showBooksGrid() {
		if (booksGrid.getAdapter() == null) {
			BookItemAdapter adapter = new BookItemAdapter(
					this.getApplicationContext());
			booksGrid.setAdapter(adapter);
		}
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
		Book book=(Book)((BookItemAdapter) booksGrid.getAdapter()).getItem(position);
		detailsActivityIntent.putExtra("book", book);
		startActivity(detailsActivityIntent);

	}
}
