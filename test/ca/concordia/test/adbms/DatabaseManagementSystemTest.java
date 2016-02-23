
package ca.concordia.test.adbms; 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.adbms.DatabaseManagementSystem;
import ca.concordia.adbms.conf.Configuration;


public class DatabaseManagementSystemTest {

	

	@Before
	public void setUp() {
		
	}

	@After
	public void tearDown() {
		//new File(reporter.getFilePath()).delete();
	}

	
	
	@Test
	public void testCanStartLibrary() {
	}

	@Test
	public void testReadPersonFile() {
		DatabaseManagementSystem dbms = new DatabaseManagementSystem(DatabaseManagementSystem.SELECT); 
		dbms.setMaxMemory(Configuration.MAX_MEMORY);
		dbms.registerTable(Configuration.PERSON_TABLE);
		dbms.getTable(Configuration.PERSON_TABLE).createIndex(Configuration.PERSON_TABLE_AGE_COLUMN);
		
		
		//Map<String, Book> books = new HashMap<String, Book>();
		//Map<String, Map<String, Account>> accounts = new HashMap<String, Map<String, Account>>();
		//LibraryServerImpl testLibrary = new LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books, accounts);
		//assertNotNull(testLibrary);
	}
	
	public void testParsePersonRecords() {
		DatabaseManagementSystem dbms = new DatabaseManagementSystem(DatabaseManagementSystem.SELECT); 
	}	
	
	public void testCalculateAgerageIncomePerAgeRange(){
		DatabaseManagementSystem dbms = new DatabaseManagementSystem(DatabaseManagementSystem.SELECT); 
	}	


	
	
	
	@Test
	public void testCanReserveBook() {
		//HashMap<String, Book> books = new HashMap<String, Book>();
		//books.put(theBookOfNegros.getCode(), theBookOfNegros);
		//books.put(macbeth.getCode(), macbeth);
		//books.put(hamlet.getCode(), hamlet);
		//books.put(lavale.getCode(), lavale);
		//Map<String, Map<String, Account>> accounts = new HashMap<String, Map<String, Account>>();
		//LibraryServerImpl testLibrary = new LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books, accounts);
	}

	
}