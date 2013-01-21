package com.iteralab.booklist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
	private Context context;
	private List<Book> books;

	public BookItemAdapter(Context context) {
		this.context = context;
		books=new ArrayList<Book>();
		refreshItems();
	}
	
	public void refreshItems(){
		books=BookListHelper.getBooks();
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
		return Long.parseLong(books.get(position).getBookId());
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);
			gridView = inflater.inflate(R.layout.book_item, null);
			
			TextView nameView=(TextView)gridView.findViewById(R.id.name_txtview);
			TextView authorView=(TextView)gridView.findViewById(R.id.author_txtview);
			
			nameView.setText(books.get(position).getName());
			authorView.setText(books.get(position).getAuthor());
			
			

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

}
