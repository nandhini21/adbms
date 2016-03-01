package ca.concordia.adbms.conf;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	
	//Memory in bytes
	public static final Integer MAIN_MEMORY = 52428800;//50MB = 50 * 1024 * 1024 Bytes
	public static final String PERSON_TABLE = "person.txt";
	public static final String PERSON_TABLE_AGE_COLUMN = "AGE";
	public static final String PERSON_FILE = "data/db/person.txt";
	public static final int TUPLE_SIZE = 100;//a tupple has 100 bytes 
	public static final int BLOCK_SIZE = 4046;//a tupple has 100 bytes 
	

	//allowed commands 
	public static final int SELECT = 0; 
	public static final int EXIT = 1; 
	public static String[] ALLOWED_COMMANDS = new String[] { "select", "exit" };
	/**
	 * This construct is built based on thoughts found at 
	 * @link http://stackoverflow.com/a/6802502/132610
	 */
	private static final Map<String,String> help; 
	static {
		help = new HashMap<String,String>();
        help.put("select", "**This program show you income of people having a certain age. \"SELECT  -max [max age number] -min [minimum age number]\" on prompt prompt **%s");
        help.put("exit", "**To close this application, type \"exit\"** to prompt. %s");
	}
	public static final Map<String,String> getCommandHelp(){
		 return help;
	}
	
	public static final String getApplicationTitle(){
		return "Welcome to ADVANCED DBMS - DataTron\r\n"
				+"You will be able to find people and their income by typing max, min age\r\n";
	}
	
}