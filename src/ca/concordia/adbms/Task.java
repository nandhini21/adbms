package ca.concordia.adbms;

import ca.concordia.adbms.ExitException;

public interface Task{
	public void execute( ) throws ExitException;
}
