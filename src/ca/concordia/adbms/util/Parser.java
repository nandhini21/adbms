package ca.concordia.adbms.util;

import java.util.Arrays;

import ca.concordia.adbms.SelectQuery;
import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.schema.PersonSchema;



public class Parser {
	
	
	/**
	 * @link http://stackoverflow.com/a/4951683/132610
	 * @link http://stackoverflow.com/a/4951639/132610
	 * Function to parse a person tuple, and returns a Person Schema
	 * @param buffer
	 * @param buffer
	 * @return Person
	 */
	public static Person parse(byte [] buffer, int offset){
		Person person = new Person();
		//incomplete tuples or null rows are not processed 
		if(buffer.length <= 0 ) { 
			return person; 
		}
		person.setSin(new String(buffer, PersonSchema.getSinOffset()+offset, PersonSchema.SIN));
		person.setFirstName(new String(buffer, PersonSchema.getFirstNameOffset()+offset, PersonSchema.FIRST));
		person.setLastName(new String(buffer, PersonSchema.getLastNameOffset()+offset, PersonSchema.LAST));
		person.setAge(Integer.parseInt(new String(buffer, PersonSchema.getAgeOffset()+offset, PersonSchema.AGE)));
		person.setIncome(Integer.parseInt(new String(buffer, PersonSchema.getIncomeOffset()+offset, PersonSchema.INCOME)));
		person.setAddress(new String(buffer, PersonSchema.getAddressOffset()+offset, PersonSchema.ADDRESS));
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
	 * This function parse Select options
	 * @param arg
	 * @return SelectQuery 
	 */
	public static SelectQuery parseSelect(String arg){
		int min = -1;  int age = -1; int max = -1; 
		String[] parsed = arg.split(" ");
		int minIndex = java.util.Arrays.asList(parsed).indexOf("-min");
		int ageIndex = java.util.Arrays.asList(parsed).indexOf("-age");
		int maxIndex = java.util.Arrays.asList(parsed).indexOf("-max");
		if( minIndex > -1 && java.util.Arrays.asList(parsed).get(minIndex + 1) != null ){ 
			min = Integer.parseInt(java.util.Arrays.asList(parsed).get(minIndex + 1));
		}
		if( ageIndex > -1 &&  java.util.Arrays.asList(parsed).get(ageIndex + 1) != null ){
			age = Integer.parseInt(java.util.Arrays.asList(parsed).get(ageIndex + 1));
		}
		if( maxIndex > -1 &&  java.util.Arrays.asList(parsed).get(maxIndex + 1) != null ){ 
			max = Integer.parseInt(java.util.Arrays.asList(parsed).get(maxIndex + 1));
		}
		return new SelectQuery(min, age, max); 
	}
}
