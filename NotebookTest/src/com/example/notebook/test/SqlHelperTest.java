package com.example.notebook.test;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import android.test.AndroidTestCase;

import com.example.notebook.Person;
import com.example.notebook.SqlHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SqlHelperTest extends AndroidTestCase {

	SqlHelper db;
	int id;

	public SqlHelperTest() {
		super();
	}

	@BeforeClass
	protected void setUp() throws Exception {
		super.setUp();
		db = new SqlHelper(mContext);
		id = db.getAllPeople().size() + 1;
		assertFalse(db.getAllPeople().isEmpty());
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test_A_getAllPeople() {
		assertFalse(db.getAllPeople().isEmpty());
	}
	
	@Test
	public void test_B_addPerson() {
		int sizeInit = db.getAllPeople().size();
		db.addPerson("IIT", "SAT", "3125673000", "3300 S Federal Street",
				(float) 41.83, (float) -87.63, "05/24/1991");
		int sizeFinal = db.getAllPeople().size();
		assertEquals("Person should be added", sizeInit + 1, sizeFinal);
	}

	@Test
	public void test_C_getPerson() {
		Person p = db.getPerson(id);
		assertEquals(
				"Should get the same person",
				"Person [id=14, firstName=IIT, lastName=SAT, phoneNumber=3125673000, address=3300 S Federal Street, latitude=41.83, longitude=-87.63, dob=05/24/1991]",
				p.toString());
	}

	@Test
	public void test_D_updatePerson() {
		db.updatePerson(id, "IIT", "SAT", "3125673333",
				"3300 S Federal Street", (float) 41.83, (float) -87.63,
				"05/24/1991");
		assertEquals("Phone number should be updated", "3125673333", db
				.getPerson(id).getPhoneNumber());
	}

	@Test
	public void test_E_deletePerson() {
		db.deletePerson(id);
		assertEquals("Person should be deleted",null,db.getPerson(id));
	}

	@Test
	public void test_F_getAllPeopleSearch() {
		int numberSearched = db.getAllPeopleSearch("m").size();
		assertEquals("There should be 3 results",3, numberSearched);
	}

}
