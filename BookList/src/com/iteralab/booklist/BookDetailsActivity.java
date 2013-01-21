package com.iteralab.booklist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class BookDetailsActivity extends Activity {
	private final String TAG = BookDetailsActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		Intent inputIntent = getIntent();
		Bundle inputBundle = inputIntent.getExtras();

		Book book=inputBundle.getParcelable("book");

				
		String name = book.getName();
		String author = book.getAuthor();
		String genre = book.getGenre();
		boolean electronicVar = book.getEVariantPresent();
		String publishDate =BookListHelper.convertDateToString(book.getPublishDate());

		TextView nameView = (TextView) findViewById(R.id.name_view);
		TextView authorView = (TextView) findViewById(R.id.author_view);
		TextView genreView = (TextView) findViewById(R.id.genre_view);
		CheckBox electronicVarView = (CheckBox) findViewById(R.id.evarient_checkbox);
		TextView publishDateView = (TextView) findViewById(R.id.publish_date_view);

		nameView.setText(name);
		authorView.setText(author);
		genreView.setText(genre);
		publishDateView.setText(publishDate);
		electronicVarView.setChecked(electronicVar);

		TextView backToListBtn = (TextView) findViewById(R.id.back_to_list);
		backToListBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						BookListActivity.class));

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_book_details, menu);
		return true;
	}

}
