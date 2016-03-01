package ca.concordia.adbms;

import java.util.Map;


/**
 * @deprecated Implementation is done in client class 
 * @todo --- relized that this class is not needed. 
 * There is a SelectTask that is doing this job better. 
 */
public class DataTron {

	
	
	public static final String SELECT = "SELECT * FROM PERSON WHERE AGE < 50 && AGE > 20";
	@SuppressWarnings("unused")
	private Integer maxMemory;
	private Map<String, Table> tables;
	
	
	public DataTron(){}
	
	/**
	 * The constructor of this file is temporal. 
	 * It will serve to read and parse the file into valid data types  
	 * @param selector
	 */
	public DataTron(String selector) {
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
