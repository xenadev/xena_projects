package com.journal;

import java.util.ArrayList;
import java.util.List;

/**
 * List representation of journal.
 * 
 * @author k.shaposhnik
 * 
 */
public class ListJournal {
	private static ListJournal instance;

	private List<Record> records;

	private ListJournal() {
		records = new ArrayList<Record>();
		fillJournal();

	}

	public static ListJournal getInstance() {
		if (instance == null) {
			synchronized (ListJournal.class) {
				if (instance == null) {
					instance = new ListJournal();
				}
			}

		}

		return instance;

	}

	private void fillJournal() {
		records.add(new Record("1"));
		records.add(new Record("2"));
		records.add(new Record("3"));
		records.add(new Record("4"));
		records.add(new Record("5"));
		records.add(new Record("6"));
		records.add(new Record("7"));
		records.add(new Record("8"));
		records.add(new Record("9"));
		records.add(new Record("10"));

	}

	public List<Record> getRecords() {
		return records;
	}

}
