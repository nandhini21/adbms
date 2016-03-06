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
		assertTrue(Configuration.MEMORY_SIZE == (5*1024*1024) || Configuration.MEMORY_SIZE == (2*1024*1024));
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
	@Test public void testCanCountPassesByMemoryConfiguration() throws IOException, ExitException{
		MemoryManager memoryManager = new MemoryManager(); 
		Task task = new SelectTask("select -age 53"); 
		task.setMemoryManager(memoryManager);
		task.execute(); 
		assertEquals(task.getMemoryManager().getResultSize(), 136);
		//for 4KB block(read once) count in following scenarios
		assertEquals(task.getMemoryManager().getTableFileSize(), 1000000);
		//Using 2MB for main memory
		//Using 5MB for main memory 
		assertEquals(task.getMemoryManager().getFileReadPasses(), 781); 
		
	}

	@Test public void testCanNotExceedPasses() throws IOException, ExitException{
		//assertEquals()
		MemoryManager memoryManager = new MemoryManager(); 
		Task task = new SelectTask("select -age 53"); 
		task.setMemoryManager(memoryManager);
		task.execute(); 
	}
	
	

}