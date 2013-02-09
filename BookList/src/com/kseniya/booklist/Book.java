package com.kseniya.booklist;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Presents entity of a book.
 * 
 * @author TOSHIBA
 * 
 */
public class Book implements Parcelable {
	private int bookId;
	private String name;
	private String author;
	private boolean eVariantPresent;
	private String genre;
	private Date publishDate;

	public Book() {

	}

	public Book(int bookId, String name, String author,
			boolean eVariantPresent, String genre, Date publishDate) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.eVariantPresent = eVariantPresent;
		this.genre = genre;
		this.publishDate = publishDate;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getEVariantPresent() {
		return eVariantPresent;
	}

	public void setEVariantPresent(boolean eVariantPresent) {
		this.eVariantPresent = eVariantPresent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean iseVariantPresent() {
		return eVariantPresent;
	}

	public void seteVariantPresent(boolean eVariantPresent) {
		this.eVariantPresent = eVariantPresent;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(bookId);
		dest.writeString(name);
		dest.writeString(author);
		dest.writeBooleanArray(new boolean[] { eVariantPresent });
		dest.writeString(genre);
		dest.writeString(BookListUtils.convertDateToString(publishDate));

	}

	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {

		@Override
		public Book createFromParcel(Parcel source) {
			return new Book(source);
		}

		@Override
		public Book[] newArray(int size) {
			return new Book[size];
		}

	};

	private Book(Parcel in) {
		this.bookId = in.readInt();
		this.name = in.readString();
		this.author = in.readString();
		boolean[] booleanArray = new boolean[1];
		in.readBooleanArray(booleanArray);
		this.eVariantPresent = booleanArray[0];
		this.genre = in.readString();
		this.publishDate = BookListUtils.convertStringToDate(in.readString());

	}
}
