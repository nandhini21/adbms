package ca.concordia.adbms;

import java.io.IOException;

import ca.concordia.adbms.util.Parser;


/**
 * Bonjour Task is used by the client to establish connection with a given remote server.
 * @author murindwaz
 */
public class SelectTask implements Task {
	//age to select from 
	private int min; 
	private int max; 
	
	public SelectTask(String argument) throws IOException{
		String[] select = Parser.parseSelect(argument);
		min = Integer.parseInt(select[0]);
		max = Integer.parseInt(select[1]);
	}	
	

	
	public void execute(){
		
	}

}