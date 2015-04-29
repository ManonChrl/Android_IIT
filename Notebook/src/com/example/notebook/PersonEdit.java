package com.example.notebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Class to edit a person
 * 
 * @author Manon
 *
 */
public class PersonEdit extends Activity {

	SqlHelper db;
	private int id;
	EditText etFN, etLN, etPN, etA, etLat, etLong, etDOB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_edit);

		Intent i = getIntent();
		id = i.getIntExtra("id", 0);
		Log.d("Id to edit:", "" + id);
		db = new SqlHelper(this);
		Person p = db.getPerson(id);

		etFN = (EditText) findViewById(R.id.firstNameEdit);
		etFN.setText(p.getFirstName());
		etLN = (EditText) findViewById(R.id.lastNameEdit);
		etLN.setText(p.getLastName());
		etPN = (EditText) findViewById(R.id.phoneNumberEdit);
		etPN.setText(p.getPhoneNumber());
		etA = (EditText) findViewById(R.id.addressEdit);
		etA.setText(p.getAddress());
		etLat = (EditText) findViewById(R.id.latitudeEdit);
		etLat.setText("" + p.getLatitude());
		etLong = (EditText) findViewById(R.id.longitudeEdit);
		etLong.setText("" + p.getLongitude());
		etDOB = (EditText) findViewById(R.id.dobEdit);
		etDOB.setText("" + p.getDOB());

		Button buttonCancel = (Button) findViewById(R.id.cancel);
		// when button CANCEL clicked
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PersonEdit.this, PersonDetail.class);
				i.putExtra("id", id);
				// Go to person detail
				startActivity(i);
			}
		});

		Button buttonSave = (Button) findViewById(R.id.save);
		// when button SAVE clicked
		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// if the latitude is empty, write 0
				if ("" + etLat.getText() == "") {
					etLat.setText("0");
				}
				// if the longitude is empty, write 0
				if ("" + etLong.getText() == "") {
					etLong.setText("0");
				}
				// Update the person in the database
				db.updatePerson(id, "" + etFN.getText(), "" + etLN.getText(),
						"" + etPN.getText(), "" + etA.getText(),
						Float.parseFloat("" + etLat.getText()),
						Float.parseFloat("" + etLong.getText()),
						"" + etDOB.getText());
				Log.d("Person updated: ", db.getPerson(id).toString());
				Person p = db.getPerson(id);
				Toast.makeText(getApplicationContext(),
						p.getFirstName() + " " + p.getLastName() + " updated",
						Toast.LENGTH_LONG).show();

				Intent i = new Intent(PersonEdit.this, PersonDetail.class);
				i.putExtra("id", id);
				// Go to person detail
				startActivity(i);
			}
		});

		// Function to hide keyboard when layout touched
		RelativeLayout rel = (RelativeLayout) findViewById(R.id.rel);
		rel.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
				return false;
			}
		});
	}
}
