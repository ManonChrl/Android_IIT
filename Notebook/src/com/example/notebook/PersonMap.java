package com.example.notebook;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class to display the map view
 * 
 * @author Manon
 *
 */
public class PersonMap extends Activity implements OnMapReadyCallback {

	SqlHelper db;
	private int id;
	private float latitude, longitude;
	private String firstName, lastName, address;
	TextView tvMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_map);
		Intent i = getIntent();
		id = i.getIntExtra("id", 0);
		db = new SqlHelper(this);
		Person p = db.getPerson(id);
		latitude = p.getLatitude();
		longitude = p.getLongitude();
		firstName = p.getFirstName();
		lastName = p.getLastName();
		address = p.getAddress();

		Log.d("Latitude: ", "" + latitude);
		Log.d("Longitude: ", "" + longitude);

		tvMap = (TextView) findViewById(R.id.textMap);
		tvMap.setText(p.getFirstName() + " " + p.getLastName());

		Button backButton = (Button) findViewById(R.id.backMap);
		// when BACK button clicked
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PersonMap.this, PersonDetail.class);
				i.putExtra("id", id);
				// Go to person detail
				startActivity(i);
			}
		});

		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

	}

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng position = new LatLng(latitude, longitude);

		map.setMyLocationEnabled(true);
		map.setOnMyLocationChangeListener(myLocationChangeListener);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
		map.getUiSettings().setZoomControlsEnabled(true);
		map.addMarker(new MarkerOptions().title(firstName + " " + lastName)
				.snippet(address).position(position));
	}

	// Function called when the user location changes
	private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
		@Override
		public void onMyLocationChange(Location location) {
			LatLng loc = new LatLng(location.getLatitude(),
					location.getLongitude());
			Log.d("Current location", "" + location);

			// Location of address searched
			Location locAdd = new Location("");
			locAdd.setLatitude(latitude);
			locAdd.setLongitude(longitude);

			int distanceInMeters = (int) locAdd.distanceTo(location);
			Log.d("distance", "" + distanceInMeters);

			tvMap = (TextView) findViewById(R.id.textMap);

			if (distanceInMeters < 10000) {
				// display the distance in meters if inf to 10km
				tvMap.setText("I am " + distanceInMeters + " meters from "
						+ firstName + " " + lastName + "'s house.");
			} else {
				// display the distance in kilometers otherwise
				tvMap.setText("I am " + distanceInMeters / 1000
						+ " kilometers from " + firstName + " " + lastName
						+ "'s house.");

			}
		}
	};

}
