

import static org.junit.Assert.*;

import java.io.File;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.adbms.DatabaseManagementSystem;


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
	public void testCanReadPersonFile() {
		new DatabaseManagementSystem(DatabaseManagementSystem.SELECT); 
		
		//Map<String, Book> books = new HashMap<String, Book>();
		//Map<String, Map<String, Account>> accounts = new HashMap<String, Map<String, Account>>();
		//LibraryServerImpl testLibrary = new LibraryServerImpl(Configuration.INSTITUTION_CONCORDIA, books, accounts);
		//assertNotNull(testLibrary);
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