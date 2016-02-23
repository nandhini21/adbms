package ca.concordia.adbms;

import java.util.Map;


/**
 * This class will manage tables, knows about database size etc. 
 * @author murindwaz
 */
public class DatabaseManagementSystem {

	
	
	public static final String SELECT = "SELECT * FROM PERSON WHERE AGE < 50 && AGE > 20";
	@SuppressWarnings("unused")
	private Integer maxMemory;
	private Map<String, Table> tables;
	
	
	/**
	 * The constructor of this file is temporal. 
	 * It will serve to read and parse the file into valid data types  
	 * @param selector
	 */
	public DatabaseManagementSystem(String selector) {
		// TODO Auto-generated constructor stub
	}


	//Configures the max memory allowed on each read 
	public void setMaxMemory(Integer maxMemory) {
		this.maxMemory = maxMemory; 
	}


	public void registerTable(String personTable) {
		//@todo check if there is another table, and ignore
		//@todo check file size, and determine which algorithm to use. 
		//@todo check index and create secondary index 
		
	}

	//checks and returns a table from DBMS 
	public Table getTable(String tableName) {
		return tables.get(tableName);
	}

	
}
