package com.kseniya.booklist;

public enum BookGenre {
	STUDY("Study"), FICTION("Fiction"), SCIENCE("Science"), DETECTIVE(
			"Detective"), ROMANCE("Romance");
	public String value;

	private BookGenre(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
