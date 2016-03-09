package ca.concordia.adbms;

import java.io.IOException;
import java.util.Arrays;

import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.util.Parser;


/**
 * This factory creates an executable task 
 */
public class TaskFactory {
	public static Task create(String argument) throws IOException {
		String command = Parser.parseCommand(argument);
		Task task = null;
		switch (Arrays.asList(Configuration.ALLOWED_COMMANDS).indexOf(command)) {
		case Configuration.SELECT:
			task = new SelectTask(argument);
			break;
		case Configuration.EXIT:
			task = new ExitTask(argument);
			break;
		}
		return task;
	}
}