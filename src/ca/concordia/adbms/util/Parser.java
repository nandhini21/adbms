package ca.concordia.adbms.util;

import java.util.Scanner;

import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.schema.PersonSchema;



public class Parser {
	
	
	private static Scanner scanner;
	
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
	 * @todo remove this class 
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public static Person parse(String line) throws Exception{
		Person person = new Person();
		scanner = new Scanner(line); 
		 scanner.useDelimiter(" ");
		 System.out.println( scanner );
		    if (scanner.hasNext()){
		    	person.setSin(scanner.next().trim());
			    person.setFirstName(scanner.next().trim());
			    person.setLastName(scanner.next().trim());
			    person.setAge(Integer.parseInt(scanner.next().trim()));
			    person.setIncome(Integer.parseInt(scanner.next().trim()));
			    person.setAddress(scanner.next());
		    }
		return person;
	}

}
