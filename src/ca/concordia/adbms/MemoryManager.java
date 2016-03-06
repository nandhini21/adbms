package ca.concordia.adbms;

import java.io.FileInputStream;
import java.io.IOException;

import ca.concordia.adbms.conf.Configuration;

public class MemoryManager {
	
	private int resultSize = 0;
	private long tableFileSize = -1; 
	
	public int getResultSize() {
		return resultSize;
	}
	
	public void increment() {
		resultSize = resultSize + 1; 
	}

	public void calculateFileReadPass(FileInputStream rstream) throws IOException {
		//FileChannel channel = rstream.getChannel();
		tableFileSize = rstream.getChannel().size(); 
	}
	
	public long getTableFileSize(){
		return tableFileSize; 
	}
	
	
	/**
	 * This utililty calculates the number of reads in following conditions. 
	 * 	- 5MB Main mamory  
	 * 	- 
	 * @return
	 */
	public long getFileReadPasses(){
		return tableFileSize/(Configuration.MAIN_MEMORY/Configuration.BLOCK_SIZE);
	}

}
