package ca.concordia.adbms;

public class MemoryManager {
	
	private int resultSize = 0;
	
	public int getResultSize() {
		return resultSize;
	}
	
	public void increment() {
		resultSize = resultSize + 1; 
	}

}
