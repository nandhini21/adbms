package ca.concordia.adbms.util;

import java.util.Scanner;

import ca.concordia.adbms.model.Person;



public class Parser {
	
	
	private static Scanner scanner;

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
