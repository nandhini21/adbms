package ca.concordia.test.adbms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.adbms.ExitException;
import ca.concordia.adbms.MemoryManager;
import ca.concordia.adbms.SelectQuery;
import ca.concordia.adbms.SelectTask;
import ca.concordia.adbms.Task;
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
		assertEquals(Configuration.TUPLE_SIZE, 100);
		assertEquals(Configuration.BLOCK_SIZE, (4*1024));
		assertTrue(Configuration.MAIN_MEMORY == (50*1024*1024));
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
	public void testCanFindTeenagers() throws ExitException, IOException{
		//1900000 => We have 67 People having 19 Years Old 
		//1800000 => We have 67 People having 18 Years old
		Task task = new SelectTask("select -age 19"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 119);
		
		task = new SelectTask("select -age 18"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 128);
	}
	
	@Test 
	public void testCanFindSeniors() throws ExitException, IOException{
		//8200000 => we have 47 People having 82 years old 
		//8700000 => we have 67 People having 87 Years old 
		Task task = new SelectTask("select -age 82"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 95);
		
		task = new SelectTask("select -age 87"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 115);
	}
	@Test 
	public void testCanFindMiddleAged() throws ExitException, IOException{
		//6600000 => we have 66 People having 66 Years old
		//5300000 => We have 71 People having 53 Years old 
		Task task = new SelectTask("select -age 66"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 135);
		
		task = new SelectTask("select -age 53"); 
		task.setMemoryManager( new MemoryManager());
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 136);
	}
	


	@Test
	public void testAverageIncome() {
		//for all age groups
		//
	}
	@Test public void testCanGroupByAge(){}
	@Test public void testCanCountPassesByMemoryConfiguration(){
		//for 4KB block(read once) count in following scenarios
		//Using 2MB for main memory
		//Using 5MB for main memory 
	}
	@Test public void testCanNotExceedPasses(){}
	
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

}