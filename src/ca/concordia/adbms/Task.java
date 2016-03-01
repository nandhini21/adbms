package ca.concordia.adbms;

import ca.concordia.adbms.ExitException;

public interface Task{
	public void execute( ) throws ExitException;
	public void setMemoryManager(MemoryManager memoryManager);
	public MemoryManager getMemoryManager();
}
