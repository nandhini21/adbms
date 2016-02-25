package ca.concordia.test.adbms;

//import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.adbms.DataTron;
import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.util.Parser;

public class DataTronTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		// new File(reporter.getFilePath()).delete();
	}

	@Test
	public void testCanDetectRows() throws Exception {
		String testString = "932512143mhSznrWOVy     gOPhwPenh      870000044954201,jXZNStreet,Montreal,QC,Canada                932512144xjtfDtTt       hPOAwdPea      660000050072554,CWSFGStreet,Montreal,QC,Canada               932512145eTvcDpIGjbAyJ  NKNqZrREB      670000071286132,iJIXStreet,Montreal,QC,Canada ";
		;
			Person person = Parser.parse(testString);
		 //assertTrue(person.getSin().equals(932512143) ); 
		 //assertTrue(person.getFirstName().equals("mhSznrWOVy") ); 
		 //assertTrue(person.getLastName().equals("gOPhwPenh") ); 
		 //assertTrue(person.getAge().equals(87));// 
		 //assertTrue(person.getIncome().equals(44954201)); 
		 //assertTrue(person.getAddress().equals("jXZNStreet,Montreal,QC,Canada")); 
	}

	@Test
	public void testReadPersonFile() {
		//DataTron dbms = new DataTron(DataTron.SELECT);
		//dbms.setMaxMemory(Configuration.MAX_MEMORY);
		//dbms.registerTable(Configuration.PERSON_TABLE);
		//dbms.getTable(Configuration.PERSON_TABLE).createIndex(Configuration.PERSON_TABLE_AGE_COLUMN);

		// Map<String, Book> books = new HashMap<String, Book>();
		// Map<String, Map<String, Account>> accounts = new HashMap<String,
		// Map<String, Account>>();
		// LibraryServerImpl testLibrary = new
		// LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books,
		// accounts);
		// assertNotNull(testLibrary);
	}

	//public void testParsePersonRecords() {
	//	DataTron dbms = new DataTron(DataTron.SELECT);
	//}

	//public void testCalculateAgerageIncomePerAgeRange() {
	//	DataTron dbms = new DataTron(DataTron.SELECT);
	//}

	//@Test
	//public void testCanReserveBook() {
		// HashMap<String, Book> books = new HashMap<String, Book>();
		// books.put(theBookOfNegros.getCode(), theBookOfNegros);
		// books.put(macbeth.getCode(), macbeth);
		// books.put(hamlet.getCode(), hamlet);
		// books.put(lavale.getCode(), lavale);
		// Map<String, Map<String, Account>> accounts = new HashMap<String,
		// Map<String, Account>>();
		// LibraryServerImpl testLibrary = new
		// LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books,
		// accounts);
	//}

}