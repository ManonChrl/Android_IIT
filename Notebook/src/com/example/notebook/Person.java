package com.example.notebook;

/**
 * This class implements a Person
 * 
 * @author Manon
 *
 */
public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private float latitude;
	private float longitude;
	private String dob;

	/**
	 * Empty constructor
	 */
	public Person() {
	}

	/**
	 * Full constructor
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
	 *            date of birth of the person
	 */
	public Person(String firstName, String lastName, String phoneNumber,
			String address, float latitude, float longitude, String dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dob = dob;

	}

	/**
	 * Get the id of the person
	 * 
	 * @return the id of the person
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of the person
	 * 
	 * @param id
	 *            id of the person
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the first name of the person
	 * 
	 * @return the first name of the person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of the person
	 * 
	 * @param firstName
	 *            first name of the person
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name of the person
	 * 
	 * @return the last name of the person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of the person
	 * 
	 * @param lastName
	 *            the last name of the person
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the phone number of the person
	 * 
	 * @return the phone number of the person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number of the person
	 * 
	 * @param phoneNumber
	 *            the phone number of the person
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get the address of the person
	 * 
	 * @return the address of the person
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of the person
	 * 
	 * @param address
	 *            the address of the person
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the latitude of the address
	 * 
	 * @return the latitude of the address
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Set the latitude of the address
	 * 
	 * @param latitude
	 *            the latitude of the address
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get the longitude of the address
	 * 
	 * @return the longitude of the address
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Set the longitude of the address
	 * 
	 * @param longitude
	 *            the longitude of the address
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get the date of birth of the person
	 * 
	 * @return the date of birth of the person
	 */
	public String getDOB() {
		return dob;
	}

	/**
	 * Set the date of birth of the person
	 * 
	 * @param dob
	 *            the date of birth of the person
	 */
	public void setDOB(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", latitude=" + latitude + ", longitude="
				+ longitude + ", dob=" + dob + "]";
	}

}
