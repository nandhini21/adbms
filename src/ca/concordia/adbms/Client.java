package ca.concordia.adbms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketException;

import org.apache.log4j.Logger;

import ca.concordia.adbms.conf.Configuration;




/**
 * This program takes input from user.
 * Input will be of form "Select from person where age < 50 age > 12"
 * Output of the program will be the table of  
 * @author murindwaz
 */
public class Client {
	
	/**
	 * @param args
	 * @throws SocketException
	 */
	public static void main(String[] args) throws SocketException {
		Logger logger = Logger.getLogger(Client.class);
		logger.info("Starting application ");
		Task task;
		try {
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			boolean session = true;
			String institution = null;
			String argument = "";
			//booting client application
			System.out.println(Configuration.getApplicationTitle() );
			//a session is available till user exits  
			while(session){
				logger.info("Starting client's session");
				System.out.printf(Configuration.getCommandHelp().get( Configuration.ALLOWED_COMMANDS[ Configuration.SELECT ] ), "\r\n >" );
				boolean valid = false;
				while( !valid ){
					argument = new String(keyboard.readLine() ).trim().replaceAll("\\s+", " ");
					try{
						task = TaskFactory.create(argument);
						if(task instanceof BonjourTask){
						}
						if(task != null ){ 
							task.execute();
						}else{
							System.out.println(" ------ YOU HAVE TO START OVER AGAIN.---- \r\n > " );
						}
					}catch(ExitException e ){
						valid = false; 
						session = false;
						System.out.println( e.getMessage() );
					}catch(Exception e){
						valid = false;
						System.out.printf( "Client Error :%s \r\n >",  e.getCause()!= null && !e.getCause().getMessage().equals(null)? e.getCause().getMessage() : e.getMessage() );
					}
				}
			}			
			logger.info( "Ending application " );
		}catch(Exception e) {
			logger.debug( "Application fails - Exception " + e.getMessage() );
			e.printStackTrace();
		}
	}
}