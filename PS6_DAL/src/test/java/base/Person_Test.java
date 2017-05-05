package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Test() {
		
		PersonDomainModel per1 = new PersonDomainModel();
		per1.setBirthday(new Date(0));
		per1.setCity("Newark");
		per1.setFirstName("Rob");
		per1.setLastName("Banks");
		per1.setPostalCode(12345);
		per1.setStreet("123 Sesame St");
		
		UUID p1id = per1.getPersonID();
		
		//Testing AddPerson & GetPerson
		assertNull(PersonDAL.getPerson(p1id));
		PersonDAL.addPerson(per1);
		
		PersonDomainModel per2 = PersonDAL.getPerson(p1id);
		assertTrue(per2.getFirstName().equals(per1.getFirstName()));
		assertTrue(per2.getLastName().equals(per1.getLastName()));
		assertTrue(per2.getStreet().equals(per1.getStreet()));
		assertTrue(per2.getPostalCode().equals(per1.getPostalCode()));
		assertTrue(per2.getCity().equals(per1.getCity()));
		
		
		//Testing GetPersons
		ArrayList<PersonDomainModel> plist = PersonDAL.getPersons();
		assertTrue(plist.size()==1);
		
		PersonDomainModel per3 = new PersonDomainModel();
		per3.setBirthday(new Date(0));
		per3.setCity("Newark");
		per3.setFirstName("Ryan");
		per3.setLastName("Gallamoza");
		per3.setPostalCode(54321);
		per3.setStreet("321 Emases Ave");
		
		UUID p3id = per3.getPersonID();
		
		PersonDAL.addPerson(per3);
		plist = PersonDAL.getPersons();
		assertTrue(plist.size()==2);
		
		//Testing UpdatePerson
		per1.setCity("Los Angeles");
		per1.setStreet("999 West St");
		
		PersonDAL.updatePerson(per1);
		
		PersonDomainModel per4 = PersonDAL.getPerson(p1id);
		assertTrue(per4.getStreet().equals("999 West St"));
		assertTrue(per4.getCity().equals("Los Angeles"));
		
		//Testing DeletePerson
		PersonDAL.deletePerson(p1id);
		PersonDAL.deletePerson(p3id);
		assertNull(PersonDAL.getPerson(p1id));
		assertNull(PersonDAL.getPerson(p3id));
		
	}

}
