package com.example.notebook.test;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.example.notebook.Person;
import com.example.notebook.SqlHelper;

public class PersonTest extends AndroidTestCase {

	SqlHelper db;
	Person p;
	
	public PersonTest() {
		super();
	}

	@BeforeClass
	protected void setUp() throws Exception {
		super.setUp();
		db = new SqlHelper(mContext);
		p = db.getPerson(1);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_getId() {
		assertEquals("IDs should be equal",1,p.getId());
	}
	
	@Test
	public void test_getFirstName() {
		assertEquals("First names should be equal","Manon",p.getFirstName());
	}
	
	@Test
	public void test_getLastName() {
		assertEquals("Last names should be equal","Chancereul",p.getLastName());
	}
	
	@Test
	public void test_getPhoneNumber() {
		assertEquals("Phone numbers should be equal","8723011934",p.getPhoneNumber());
	}
	
	@Test
	public void test_getAddress() {
		assertEquals("Addresses should be equal","3521 N Reta ave",p.getAddress());
	}
	
	@Test
	public void test_getLatitude() {
		assertEquals("Latitudes should be equal",(float)41.95,p.getLatitude());
	}
	
	@Test
	public void test_getLongitude() {
		assertEquals("Longitudes should be equal",(float)-87.65,p.getLongitude());
	}
	
	@Test
	public void test_getDOB() {
		assertEquals("Latitudes should be equal","08/13/1993",p.getDOB());
	}
	
	@Test
	public void test_setId() {
		Person p = new Person();
		p.setId(15);
		assertEquals("Ids should be equal",15,p.getId());
	}
	
	@Test
	public void test_setFirstName() {
		Person p = new Person();
		p.setFirstName("Jade");
		assertEquals("First names should be equal","Jade",p.getFirstName());
	}
	
	@Test
	public void test_setLastName() {
		Person p = new Person();
		p.setLastName("Chancereul");;
		assertEquals("Last names should be equal","Chancereul",p.getLastName());
	}
	
	@Test
	public void test_setPhoneNumber() {
		Person p = new Person();
		p.setPhoneNumber("0241438926");;
		assertEquals("Phone numbers should be equal","0241438926",p.getPhoneNumber());
	}
	
	@Test
	public void test_setAddress() {
		Person p = new Person();
		p.setAddress("111 avenue victor chatenay");;
		assertEquals("Addresses should be equal","111 avenue victor chatenay",p.getAddress());
	}
	
	@Test
	public void test_setLatitude() {
		Person p = new Person();
		p.setLatitude((float)41.75);
		assertEquals("Latitudes should be equal",(float)41.75,p.getLatitude());
	}
	
	@Test
	public void test_setLongitude() {
		Person p = new Person();
		p.setLongitude((float)-84.55);;
		assertEquals("Longitudes should be equal",(float)-84.55,p.getLongitude());
	}
	
	@Test
	public void test_setDOB() {
		Person p = new Person();
		p.setDOB("10/04/2005");;
		assertEquals("Date of births should be equal","10/04/2005",p.getDOB());
	}
}
