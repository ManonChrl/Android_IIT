package com.example.notebook;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class sets the data on the list view
 * 
 * @author Manon
 *
 */
public class ListAdapter extends ArrayAdapter<Person> {
	private List<Person> persons;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param textViewResourceId
	 */
	public ListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param resource
	 * @param persons
	 */
	public ListAdapter(Context context, int resource, List<Person> persons) {
		super(context, resource, persons);
		this.persons = persons;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			// Inflate the list view
			LayoutInflater vi;
			vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.personlistrow, null);
		}
		// Get the person from a specific position
		Person p = getItem(position);
		// Display the person on the item list row
		if (p != null) {
			TextView tt1 = (TextView) v.findViewById(R.id.firstName);
			TextView tt2 = (TextView) v.findViewById(R.id.lastName);

			if (tt1 != null) {
				tt1.setText(p.getFirstName());
			}
			if (tt2 != null) {
				tt2.setText(p.getLastName());
			}
		}
		return v;
	}
}
