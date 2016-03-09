package ca.concordia.adbms;

import ca.concordia.adbms.conf.Configuration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 * This program takes input from user. Input will be of form "Select from person
 * where age < 50 age > 12" Output of the program will be the table of
 *
 * @author murindwaz
 */
public class Client {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Task task;
        String argument;
        BufferedReader keyboard;
        boolean session = true;
        Logger logger = Logger.getLogger(Client.class);
        logger.info("Starting application ");
        MemoryManager memoryManager = new MemoryManager();
        IndexManager indexManager = new IndexManager();
        memoryManager.startTimer();
        indexManager.createIndex(new FileInputStream(new File(Configuration.PERSON_FILE)));//initializes
        memoryManager.stopTimer();
        memoryManager.report(); 
        memoryManager.reset();
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(Configuration.getApplicationTitle());
            //a session is available till user exits  
            while (session) {
                logger.info("Starting client's session");
                System.out.println(Configuration.getCommandHelp().get(Configuration.ALLOWED_COMMANDS[ Configuration.SELECT]));
                boolean valid = false;
                while (!valid) {
                    System.out.println("\r\n ENTER YOUR QUERY HERE >");
                    argument = new String(keyboard.readLine()).trim().replaceAll("\\s+", " ");
                    try {
                        task = TaskFactory.create(argument);
                        if (task instanceof SelectTask) {
                        	memoryManager.reset();
                            task.setMemoryManager(memoryManager);
                            task.setIndexManager(indexManager);
                        }
                        if (task != null) {
                            task.execute();
                            memoryManager.report();
                        } else {
                            System.out.println(" ---SOMETHING WENT HORRIBLY WRONG ---SORRY, BUT YOU HAVE TO START OVER AGAIN.---- \r\n ENTER YOUR QUERY HERE >");
                        }
                    } catch (ExitException e) {
                        valid = false;
                        session = false;
                        keyboard.close();
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        valid = false;
                        System.out.printf("Client Error :%s \r\n >", e.getCause() != null && !e.getCause().getMessage().equals(null) ? e.getCause().getMessage() : e.getMessage());
                    }
                }
            }
            logger.info("Ending application ");
        } catch (Exception e) {
            logger.debug("Application fails - Exception " + e.getMessage());
            e.printStackTrace();
        }
    }
}
