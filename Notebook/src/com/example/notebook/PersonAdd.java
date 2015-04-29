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
 * Class to add a person
 * 
 * @author Manon
 *
 */
public class PersonAdd extends Activity {

	SqlHelper db;
	EditText etFN, etLN, etPN, etA, etLat, etLong, etDOB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_add);
		db = new SqlHelper(this);
		etFN = (EditText) findViewById(R.id.firstNameAdd);
		etLN = (EditText) findViewById(R.id.lastNameAdd);
		etPN = (EditText) findViewById(R.id.phoneNumberAdd);
		etA = (EditText) findViewById(R.id.addressAdd);
		etLat = (EditText) findViewById(R.id.latitudeAdd);
		etLong = (EditText) findViewById(R.id.longitudeAdd);
		etDOB = (EditText) findViewById(R.id.birthdayAdd);

		Button buttonCancel = (Button) findViewById(R.id.cancelAdd);
		// when button CANCEL clicked
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Go to the main activity
				Intent i = new Intent(PersonAdd.this, MainActivity.class);
				startActivity(i);
			}
		});

		Button buttonAdd = (Button) findViewById(R.id.saveAdd);
		// when button SAVE clicked
		buttonAdd.setOnClickListener(new View.OnClickListener() {

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
				// add the person to the database
				db.addPerson("" + etFN.getText(), "" + etLN.getText(), ""
						+ etPN.getText(), "" + etA.getText(),
						Float.parseFloat("" + etLat.getText()),
						Float.parseFloat("" + etLong.getText()),
						"" + etDOB.getText());
				Log.d("Person added: ",
						"" + etFN.getText() + " " + etLN.getText());
				// Toast message
				Toast.makeText(getApplicationContext(),
						etFN.getText() + " " + etLN.getText() + " added",
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(PersonAdd.this, MainActivity.class);
				// Go back to the main activity
				startActivity(i);
			}
		});

		// Function to hide keyboard when layout touched
		RelativeLayout rel = (RelativeLayout) findViewById(R.id.relAdd);
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
