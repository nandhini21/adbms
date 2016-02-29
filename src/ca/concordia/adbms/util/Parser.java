package ca.concordia.adbms.util;

import java.util.Arrays;

import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.schema.PersonSchema;



public class Parser {
	
	
	/**
	 * @link http://stackoverflow.com/a/4951683/132610
	 * @link http://stackoverflow.com/a/4951639/132610
	 * Function to parse a person tuple, and returns a Person Schema
	 * @param buffer
	 * @return Person
	 */
	public static Person parse(byte [] buffer){
		Person person = new Person();
		//incomplete tuples or null rows are not processed 
		if(buffer.length <= 0 ) { 
			return person; 
		}
		person.setSin(new String(buffer, PersonSchema.getSinOffset(), PersonSchema.SIN));
		person.setFirstName(new String(buffer, PersonSchema.getFirstNameOffset(), PersonSchema.FIRST));
		person.setLastName(new String(buffer, PersonSchema.getLastNameOffset(), PersonSchema.LAST));
		person.setAge(Integer.parseInt(new String(buffer, PersonSchema.getAgeOffset(), PersonSchema.AGE)));
		person.setIncome(Integer.parseInt(new String(buffer, PersonSchema.getIncomeOffset(), PersonSchema.INCOME)));
		person.setAddress(new String(buffer, PersonSchema.getAddressOffset(), PersonSchema.ADDRESS));
		return person; 
	}
	
	

	/**
	 * This function is used to detect which Task to execute 
	 * Gets the very first argument 
	 * @param arg
	 * @return
	 */
	public static String parseCommand(String arg) {
		String command = arg.split("(\\-[a-zA-Z])")[0].trim();
		if( Arrays.asList(new String[]{ "select","exit"}).contains(command) ){
			return command;
		}
		return  null;
	}
	
	/**
	 * This function parses number values 
	 * @param arg
	 * @return
	 */
	public static String [] parseSelect(String arg){
		String[] parsed = arg.split("(\\-[a-zA-Z])");
		return new String[]{ parsed[1].trim(), parsed[2].trim() };
	}
	
	
}
