package ca.concordia.adbms;

import ca.concordia.drms.orb.RemoteException;

public interface Task{
	public void execute( ) throws ExitException, RemoteException;
}
