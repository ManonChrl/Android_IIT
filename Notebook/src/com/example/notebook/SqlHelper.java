package com.example.notebook;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Create the database and implement CRUD operations
 * 
 * @author Manon
 * 
 */
public class SqlHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "PeopleDB";
	// People table name
	private static final String TABLE_PEOPLE = "people";

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public SqlHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create people table
		String CREATE_PERSON_TABLE = "CREATE TABLE people ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "firstName TEXT, "
				+ "lastName TEXT, phoneNumber TEXT, address TEXT,latitude FLOAT, longitude FLOAT, dob TEXT )";
		// create people table
		db.execSQL(CREATE_PERSON_TABLE);
	} 

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older people table if existed
		db.execSQL("DROP TABLE IF EXISTS people");
		// create fresh people table
		this.onCreate(db);
	}

	/**
	 * Get all people
	 * 
	 * @return the list of people
	 */
	public List<Person> getAllPeople() {
		List<Person> persons = new LinkedList<Person>();
		String query = "SELECT * FROM " + TABLE_PEOPLE
				+ " ORDER BY firstName ASC";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Person person = null;
		if (cursor.moveToFirst()) {
			do {
				person = new Person();
				person.setId(Integer.parseInt(cursor.getString(0)));
				person.setFirstName(cursor.getString(1));
				person.setLastName(cursor.getString(2));
				person.setPhoneNumber(cursor.getString(3));
				person.setAddress(cursor.getString(4));
				person.setLatitude(Float.parseFloat(cursor.getString(5)));
				person.setLongitude(Float.parseFloat(cursor.getString(6)));
				person.setDOB(cursor.getString(7));
				persons.add(person);
			} while (cursor.moveToNext());
		}
		Log.d("getAllPeople()", persons.toString());
		return persons;
	}

	/**
	 * Get a person with its id
	 * 
	 * @param id
	 *            id of the person you want
	 * @return the person
	 */
	public Person getPerson(int id) {
		String selectQuery = "select * from " + TABLE_PEOPLE + " where id=?";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { "" + id });
		Person person = null;
		if (cursor.moveToFirst()) {
			do {
				person = new Person();
				person.setId(Integer.parseInt(cursor.getString(0)));
				person.setFirstName(cursor.getString(1));
				person.setLastName(cursor.getString(2));
				person.setPhoneNumber(cursor.getString(3));
				person.setAddress(cursor.getString(4));
				person.setLatitude(Float.parseFloat(cursor.getString(5)));
				person.setLongitude(Float.parseFloat(cursor.getString(6)));
				person.setDOB(cursor.getString(7));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return person;
	}

	/**
	 * Delete a person from the database
	 * 
	 * @param id
	 *            id of the person to delete
	 */
	public void deletePerson(int id) {
		String deleteQuery = "delete from " + TABLE_PEOPLE + " where id=" + id;
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(deleteQuery);
	}

	/**
	 * Update a person from the database
	 * 
	 * @param id
	 *            id of the person to update
	 * @param firstName
	 *            new first name
	 * @param lastName
	 *            new last name
	 * @param phoneNumber
	 *            new phone number
	 * @param address
	 *            new address
	 * @param latitude
	 *            new latitude
	 * @param longitude
	 *            new longitude
	 * @param dob
	 *            date of birth
	 */
	public void updatePerson(int id, String firstName, String lastName,
			String phoneNumber, String address, float latitude,
			float longitude, String dob) {
		String updateQuery = "update " + TABLE_PEOPLE + " set firstName ='"
				+ firstName + "',lastName ='" + lastName + "',phoneNumber ='"
				+ phoneNumber + "',address ='" + address + "',latitude ="
				+ latitude + ",longitude =" + longitude + ",dob ='" + dob
				+ "' where id=" + id;
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(updateQuery);
	}

	/**
	 * Add a person in the database
	 * 
	 * @param firstName
	 *            first name of the person
	 * @param lastName
	 *            last name of the person
	 * @param phoneNumber
	 *            phone number of the person
	 * @param address
	 *            address of the person
	 * @param latitude
	 *            latitude of the address
	 * @param longitude
	 *            longitude of the address
	 * @param dob
	 *            date of birth
	 */
	public void addPerson(String firstName, String lastName,
			String phoneNumber, String address, float latitude,
			float longitude, String dob) {
		String addQuery = "insert into "
				+ TABLE_PEOPLE
				+ " (firstName,lastName,phoneNumber,address,latitude,longitude,dob) values ('"
				+ firstName + "','" + lastName + "','" + phoneNumber + "','"
				+ address + "'," + latitude + "," + longitude + ",'" + dob
				+ "')";
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(addQuery);
	}

	/**
	 * Get all people with search text
	 * 
	 * @param cs
	 *            the search string
	 * @return the list of people searched
	 */
	public List<Person> getAllPeopleSearch(String cs) {
		List<Person> persons = new LinkedList<Person>();
		String query = "SELECT * FROM " + TABLE_PEOPLE
				+ " WHERE firstName LIKE '" + cs + "%' ORDER BY firstName ASC";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Person person = null;
		if (cursor.moveToFirst()) {
			do {
				person = new Person();
				person.setId(Integer.parseInt(cursor.getString(0)));
				person.setFirstName(cursor.getString(1));
				person.setLastName(cursor.getString(2));
				person.setPhoneNumber(cursor.getString(3));
				person.setAddress(cursor.getString(4));
				person.setLatitude(Float.parseFloat(cursor.getString(5)));
				person.setLongitude(Float.parseFloat(cursor.getString(6)));
				person.setDOB(cursor.getString(7));
				persons.add(person);
			} while (cursor.moveToNext());
		}
		Log.d("getAllPeople()", persons.toString());
		return persons;
	}
}
