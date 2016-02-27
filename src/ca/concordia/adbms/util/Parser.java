package ca.concordia.adbms.util;

import java.util.Scanner;

import ca.concordia.adbms.model.Person;



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
		
		int length = 0;//length of SIN field
		//@todo user PersonSchema as description of how Person model will behave at database level
		//buffer, offset and length 
		//String s = new String(buffer, 0, len);
		//StringBuilder sb = new StringBuilder(new String(buffer,0,buffer.length-1));
		String sin = new String(buffer, 0, 9);
		
		length = length + 9;
		String first = new String(buffer, length, 15);
		
		length = length + 15;
		String last = new String(buffer, length, 15);
		
		length = length + 15;
		String age = new String(buffer, length, 2);
		
		length = length + 2; 
		String income = new String(buffer, length, 10);
		
		length = length + 10; 
		String address = new String(buffer, length, 49);
		
		//System.out.println("SIN " + sin + " FIRST " + first + " LAST " + last + " AGE " +  age + " INCOME " + income + " length " + length);
		System.out.println("SIN " + sin + " FIRST " + first + " LAST " + last + " AGE " +  age + " INCOME " + income + " ADDRESS " + address + " length " + length);
		return person; 
	}

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
