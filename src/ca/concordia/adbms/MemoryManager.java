package ca.concordia.adbms;

import java.io.FileInputStream;
import java.io.IOException;

import ca.concordia.adbms.conf.Configuration;

public class MemoryManager {
	
	private int resultSize = 0;
	private long tableFileSize = -1; 
	private long average = -1;
	private long execution = -1;
	
	public int getResultSize() {
		return resultSize;
	}
	
	public void increment() {
		resultSize = resultSize + 1; 
	}

	/**
	 * @param rstream FileInputStream of file whose size will be returned
	 * @throws IOException
	 */
	public void calculateFileReadPass(FileInputStream rstream) throws IOException {
		tableFileSize = rstream.getChannel().size(); 
	}
	
	public long getTableFileSize(){
		return tableFileSize; 
	}
	
	/**
	 * Function to re-initialize variables
	 */
	public void reset(){
		resultSize = 0; 
		tableFileSize = -1;
	}
	
	public void report(){
		String _report = String.format(" ====== Report ==== \r\n Execution time %d \r\n Average Income %d \r\n Results %d ", execution, average, resultSize);
		System.out.println(_report);
	}
	/**
	 * This utililty calculates the number of reads in following conditions. 
	 * 	- 5MB Main mamory  
	 * 	- 
	 * @return
	 */
	public long getFileReadPasses(){
		return tableFileSize/(Configuration.MEMORY_SIZE/Configuration.BLOCK_SIZE);
	}

}
