package com.iteralab.booklist;

import java.util.ArrayList;
import java.util.List;

import com.iteralab.db.BooksDataSource;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Custom adapter for book item.
 * 
 * @author TOSHIBA
 * 
 */
public class BookItemAdapter extends BaseAdapter {
	private final String TAG = BookItemAdapter.class.getSimpleName();
	private Context context;
	private List<Book> books;
	private BooksDataSource dataSource;

	public BookItemAdapter(Context context, BooksDataSource dataSource) {
		this.context = context;
		this.dataSource = dataSource;
		books = new ArrayList<Book>();
		refreshItems();
	}

	public void refreshItems() {
		books = dataSource.getAllBooks();		
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);
			gridView = inflater.inflate(R.layout.book_item, null);

			TextView nameView = (TextView) gridView
					.findViewById(R.id.name_txtview);
			TextView authorView = (TextView) gridView
					.findViewById(R.id.author_txtview);

			nameView.setText(books.get(position).getName());
			authorView.setText(books.get(position).getAuthor());

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

}
