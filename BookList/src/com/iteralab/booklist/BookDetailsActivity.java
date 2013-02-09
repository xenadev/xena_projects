package com.iteralab.booklist;

import com.iteralab.db.BooksDataSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class BookDetailsActivity extends Activity {
	private final String TAG = BookDetailsActivity.class.getSimpleName();
	private BooksDataSource dataSource;
	private Context activityContext = this;
	private Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		Intent inputIntent = getIntent();
		Bundle inputBundle = inputIntent.getExtras();

		book = inputBundle.getParcelable("book");
		updateBookViews();

		TextView backToListBtn = (TextView) findViewById(R.id.back_to_list);
		Button deleteBtn = (Button) findViewById(R.id.delete_btn);
		Button editBtn = (Button) findViewById(R.id.edit_btn);

		// set on click listeners
		backToListBtn.setOnClickListener(backToListBtnListener);
		deleteBtn.setOnClickListener(deleteBtnListener);
		editBtn.setOnClickListener(editBtnListener);

		// initialize database data source object
		dataSource = BooksDataSource.getInstance(getApplicationContext());
		if (dataSource != null) {
			dataSource.open();
		}

	}

	public void updateBookViews() {
		if (book != null) {
			TextView nameView = (TextView) findViewById(R.id.name_view);
			TextView authorView = (TextView) findViewById(R.id.author_view);
			TextView genreView = (TextView) findViewById(R.id.genre_view);

			CheckBox electronicVarView = (CheckBox) findViewById(R.id.evarient_checkbox);
			TextView publishDateView = (TextView) findViewById(R.id.publish_date_view);

			nameView.setText(book.getName());
			authorView.setText(book.getAuthor());
			genreView.setText(book.getGenre());
			publishDateView.setText(BookListUtils.convertDateToString(book
					.getPublishDate()));
			electronicVarView.setChecked(book.getEVariantPresent());

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_book_details, menu);
		return true;
	}

	OnClickListener deleteBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			AlertDialog.Builder deleteDialogBuilder = new AlertDialog.Builder(
					activityContext);
			deleteDialogBuilder.setMessage("Are you sure remove this item?")
					.setPositiveButton("Yes", dialogClickListener)
					.setNegativeButton("No", dialogClickListener).show();

		}
	};

	OnClickListener editBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent editActivityIntent = new Intent(getApplicationContext(),
					BookEditActivity.class);
			if (book != null) {
				editActivityIntent.putExtra("book", book);
			}

			startActivity(editActivityIntent);

		}
	};

	OnClickListener backToListBtnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();

		}
	};

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE: {
				if (dataSource != null && book != null) {
					int booksAffected = dataSource.deleteBook(book);
					Log.i(TAG, "name: " + book.getName());
					if (booksAffected > 0) {
						Log.i(TAG,
								"Number of books deleted: "
										+ String.valueOf(booksAffected));
						finish();
					}

				}
			}
				break;

			}
		}
	};

	@Override
	public void onPause() {
		if (dataSource != null) {
			dataSource.close();
		}
		super.onPause();
	}

}
