package ca.concordia.adbms;

import java.util.Map;



/**
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
}
