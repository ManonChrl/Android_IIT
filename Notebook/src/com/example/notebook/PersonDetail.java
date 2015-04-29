package com.example.notebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class to see the detail of a person
 * 
 * @author Manon
 *
 */
public class PersonDetail extends Activity {

	SqlHelper db;
	private int id;

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_detail);

		Intent i = getIntent();
		id = i.getIntExtra("id", 0);
		Log.d("Id:", "" + id);
		db = new SqlHelper(this);
		Person p = db.getPerson(id);

		TextView tvFN = (TextView) findViewById(R.id.firstNameDetail);
		tvFN.setText(p.getFirstName());
		TextView tvLN = (TextView) findViewById(R.id.lastNameDetail);
		tvLN.setText(p.getLastName());
		TextView tvPN = (TextView) findViewById(R.id.phoneNumberDetail);
		tvPN.setText(p.getPhoneNumber());
		TextView tvA = (TextView) findViewById(R.id.addressDetail);
		tvA.setText(p.getAddress());
		TextView tvDOB = (TextView) findViewById(R.id.dobDetail);
		tvDOB.setText(p.getDOB());

		// when the address is clicked
		tvA.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PersonDetail.this, PersonMap.class);
				i.putExtra("id", id);
				Log.d("Address clicked", "address clicked for id " + id);
				// Go to person map
				startActivity(i);
			}
		});

		Button buttonBack = (Button) findViewById(R.id.backDetail);
		// when button BACK clicked
		buttonBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PersonDetail.this, MainActivity.class);
				// Go to the main activity
				startActivity(i);
			}
		});

		Button buttonEdit = (Button) findViewById(R.id.edit);
		// when button EDIT clicked
		buttonEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PersonDetail.this, PersonEdit.class);
				i.putExtra("id", id);
				// Go to person edit
				startActivity(i);
			}
		});

		Button buttonDelete = (Button) findViewById(R.id.delete);
		// when button DELETE clicked
		buttonDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Person p = db.getPerson(id);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				// set title of dialog
				alertDialogBuilder.setTitle("Delete " + p.getFirstName() + " "
						+ p.getLastName());

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"Do you want to delete " + p.getFirstName()
										+ " " + p.getLastName() + " ?")
						.setCancelable(true)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int idClick) {
										Person p = db.getPerson(id);
										// button yes clicked, person deleted
										db.deletePerson(id);
										Log.d("Delete:", "Person with id: "
												+ id + " deleted");
										Toast.makeText(
												getApplicationContext(),
												p.getFirstName() + " "
														+ p.getLastName()
														+ " deleted",
												Toast.LENGTH_LONG).show();
										Intent i = new Intent(
												PersonDetail.this,
												MainActivity.class);
										// Go to the main activity
										startActivity(i);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// button no clicked, do nothing
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// display alert dialog
				alertDialog.show();
			}
		});
	}

}
