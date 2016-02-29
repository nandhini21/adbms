package ca.concordia.adbms;

import java.util.Scanner;

public class DB {
	public static void main(String[] args)
	{
		boolean quit=false;
		
		do
		{
			System.out.print("Type \"load\" to load index, \"query\" to query records, \"count\" to count records for key, or \"quit\" to quit");
			
			//
			Scanner scanner = new Scanner(System.in);
			String choice = scanner.next();
			
			if(choice.equals("load"))
			{
				System.out.print("\n Type the name of data file: ");
				String fileName = scanner.next();
				
				//TODO run and measure performance
			}else if(choice.equals("query"))
			{
				System.out.print("\n Typpe the age: ");
				int age = scanner.nextInt();
				
				//TODO run and measure performance
			}else if(choice.equals("count"))
			{
				System.out.print("\n Typpe the age: ");
				int age = scanner.nextInt();
				
				//TODO run and measure performance
			}else if(choice.equals("quit"))
			{
				quit = true;
			}else
			{
				System.out.println("Unknown choice");
			}
			
		}while(!quit);
	}
	

}
