package ca.concordia.adbms;


/**
 * This task exits the program by thowing ExitException 
 */
public class ExitTask implements Task {
	public ExitTask(String argument) { }
	public void execute() throws ExitException {
			throw new ExitException("******* Thanks for trying out our service ******* ");
	}
}