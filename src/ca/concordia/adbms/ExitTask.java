package ca.concordia.adbms;


/**
 * This task exits the program by thowing ExitException 
 */
public class ExitTask implements Task {
	public ExitTask(String argument) { }
	public void execute() throws ExitException {
			throw new ExitException("******* Thanks for trying out our service ******* ");
	}
	public void setMemoryManager(MemoryManager memoryManager) {
	}
	public MemoryManager getMemoryManager() {
		return null;
	}
	public void setIndexManager(IndexManager indexManager) {
		
	}
	public IndexManager getIndexManager() {
		return null;
	}
}