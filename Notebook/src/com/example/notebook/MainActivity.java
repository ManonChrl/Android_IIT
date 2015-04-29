package com.example.notebook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * This is the main activity of the app
 * @author Manon
 *
 */
public class MainActivity extends Activity {

	SqlHelper db;
	EditText search;
	ListView listContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button add = (Button) findViewById(R.id.buttonAdd);
		// when button ADD clicked
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, PersonAdd.class);
				// Go to PersonAdd
				startActivity(i);
			}
		});

		db = new SqlHelper(this);
		listContent = (ListView) findViewById(R.id.list);
		List<Person> persons = new ArrayList<Person>();
		// Get all the person from the database
		persons = db.getAllPeople();
		// get data from the table by the ListAdapter
		ListAdapter customAdapter = new ListAdapter(this,
				R.layout.personlistrow, persons);
		listContent.setAdapter(customAdapter);
		// when a person is clicked
		listContent
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView arg0, View arg1,
							int position, long arg3) {
						Intent i = new Intent(MainActivity.this,
								PersonDetail.class);
						List<Person> persons = new ArrayList<Person>();
						persons = db.getAllPeople();
						i.putExtra("id", persons.get(position).getId());
						Log.d("Person", "" + persons.get(position));
						// Go to the person detail
						startActivity(i);
					}
				});

		search = (EditText) findViewById(R.id.inputSearch);
		// when text entered in search bar
		search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				List<Person> persons = new ArrayList<Person>();
				persons = db.getAllPeopleSearch(search.getText().toString());
				Log.d("person searched", persons.toString());
				// Refresh the list of person displayed
				ListAdapter adapter2 = new ListAdapter(getApplicationContext(),
						R.layout.personlistrow, persons);
				listContent.setAdapter(adapter2);
				listContent
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView arg0,
									View arg1, int position, long arg3) {
								Intent i = new Intent(MainActivity.this,
										PersonDetail.class);
								List<Person> persons = new ArrayList<Person>();
								persons = db.getAllPeopleSearch(search
										.getText().toString());
								i.putExtra("id", persons.get(position).getId());
								Log.d("Person", "" + persons.get(position));
								startActivity(i);
							}
						});
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

		// Display a notification for today's birthday
		for (Person person : persons) {
			String dob = person.getDOB();
			if (dob != null) {
				try {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"MM/dd/yyyy");
					Date date = formatter.parse(dob);
					Date today = new Date();
					Calendar cal = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal.setTime(date);
					cal2.setTime(today);
					if (cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
						if (cal.get(Calendar.DAY_OF_MONTH) == cal2
								.get(Calendar.DAY_OF_MONTH)) {
							NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
									this)
									.setSmallIcon(R.drawable.cake)
									.setContentTitle("Birthday notification")
									.setContentText(
											"It is " + person.getFirstName()
													+ "'s birthday today");
							// when birthday notification clicked, go to person
							// detail
							Intent resultIntent = new Intent(this,
									PersonDetail.class);
							resultIntent.putExtra("id", person.getId());

							// when user tapped back, go to home screen
							TaskStackBuilder stackBuilder = TaskStackBuilder
									.create(this);
							stackBuilder.addParentStack(MainActivity.class);
							stackBuilder.addNextIntent(resultIntent);
							PendingIntent resultPendingIntent = stackBuilder
									.getPendingIntent(0,
											PendingIntent.FLAG_UPDATE_CURRENT);
							mBuilder.setContentIntent(resultPendingIntent);
							NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
							// display notification
							mNotificationManager.notify(0, mBuilder.build());
						}
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
