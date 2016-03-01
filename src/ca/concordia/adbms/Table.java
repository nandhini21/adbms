package ca.concordia.adbms;

import java.util.Map;
import java.lang.System;



/**
 * @deprecated - this class may not be needed, or not be needed in this form. 
 * Descibing a table in database 
 * @author murindwaz
 *
 */
public class Table {
	String location; 
	Map<String,String> columns; 
	//@todo the data type is not known yet 
	Map<String,String> index; 
	String bufferLocation; 
	
	//Creating a new table
	public Table(String name, Map<String, String> columns){
		
	}

	public Table createIndex(String index) {
		// TODO Auto-generated method stub
		//if(index == null ) new HashMap(index, index); 
		return this;
	}
	
	//
	public void saveIndex(String key)
	{
		
	}
	
	//
	public void query(String key)
	{
		
	}
	
	//
	public void count(String key)
	{
		
	}
	
	/** 
	 * @todo move this function to MemoryManager. 
	 * @todo we may not need to force GC to release memory, as long as there is no hold back of File content in Memory. 
	 * Wait for memory to be available and returns the available
	 */
	public long waitForAvailableMemory(int size)
	{
		long freeMemory = Runtime.getRuntime().freeMemory();
		
		while(Runtime.getRuntime().freeMemory() > size)
		{
			// Invoke the garbage collector
			System.gc();
			
			//
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return freeMemory;
	}

}
