package ca.concordia.test.adbms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.adbms.SelectQuery;
import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.util.Parser;

public class DataTronTest {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
	}

	@Test public void testConfiguration(){
		assertTrue( Configuration.MAX_MEMORY == (50*1024*1024));
		assertTrue(Configuration.PERSON_TABLE == "person.txt");
		assertTrue(Configuration.PERSON_FILE == "data/db/person.txt");
	}

	@Test 
	public void testCanInterpretInputCommand(){
		assertEquals(Parser.parseCommand("exit"), "exit"); 
		assertEquals(Parser.parseCommand("select"), "select"); 
		
		SelectQuery query = Parser.parseSelect("select -max 30 -min 20");
		assertEquals(query.getMax(), 30);//min
		assertEquals(query.getMin(), 20);//max
		assertEquals(query.getAge(), -1);//age
		
		query = Parser.parseSelect("select -age 30 -min 20 -max 40");
		assertEquals(query.getMax(), 40);//min
		assertEquals(query.getMin(), 20);//max
		assertEquals(query.getAge(), 30);//age
		
		query = Parser.parseSelect("select -age 12");
		assertEquals(query.getMax(), -1);//min
		assertEquals(query.getMin(), -1);//max
		assertEquals(query.getAge(), 12);//age
		
		query = Parser.parseSelect("select");
		assertEquals(query.getMax(), -1);//min
		assertEquals(query.getMin(), -1);//max
		assertEquals(query.getAge(), -1);//age
		
	}
	
	@Test 
	public void testCanFindTeenagers(){
		//1900000 => We have 67 People having 19 Years Old 
		//1800000 => We have 67 People having 18 Years old
	}
	@Test 
	public void testCanFindSeniors(){
		//8200000 => we have 47 People having 82 years old 
		//8700000 => we have 67 People having 87 Years old 
	}
	@Test 
	public void testCanFindMiddleAged(){
		//6600000 => we have 66 People having 66 Years old
		//5300000 => We have 71 People having 53 Years old 
	}
	


	@Test
	public void testBufferedReader() {
		
		
	}

	
	@Test
	public void testCanDetectRows() throws Exception {

		// String testString =
		// "932512143mhSznrWOVy     gOPhwPenh      870000044954201,jXZNStreet,Montreal,QC,Canada                932512144xjtfDtTt       hPOAwdPea      660000050072554,CWSFGStreet,Montreal,QC,Canada               932512145eTvcDpIGjbAyJ  NKNqZrREB      670000071286132,iJIXStreet,Montreal,QC,Canada ";
		// ;
		// Person person = Parser.parse(testString);
		// assertTrue(person.getSin().equals(932512143) );
		// assertTrue(person.getFirstName().equals("mhSznrWOVy") );
		// assertTrue(person.getLastName().equals("gOPhwPenh") );
		// assertTrue(person.getAge().equals(87));//
		// assertTrue(person.getIncome().equals(44954201));
		// assertTrue(person.getAddress().equals("jXZNStreet,Montreal,QC,Canada"));
	}

	@Test
	public void testReadPersonFile() {
		// DataTron dbms = new DataTron(DataTron.SELECT);
		// dbms.setMaxMemory(Configuration.MAX_MEMORY);
		// dbms.registerTable(Configuration.PERSON_TABLE);
		// dbms.getTable(Configuration.PERSON_TABLE).createIndex(Configuration.PERSON_TABLE_AGE_COLUMN);

		// Map<String, Book> books = new HashMap<String, Book>();
		// Map<String, Map<String, Account>> accounts = new HashMap<String,
		// Map<String, Account>>();
		// LibraryServerImpl testLibrary = new
		// LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books,
		// accounts);
		// assertNotNull(testLibrary);
	}

	// public void testParsePersonRecords() {
	// DataTron dbms = new DataTron(DataTron.SELECT);
	// }

	// public void testCalculateAgerageIncomePerAgeRange() {
	// DataTron dbms = new DataTron(DataTron.SELECT);
	// }

	// @Test
	// public void testCanReserveBook() {
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
	// }

}