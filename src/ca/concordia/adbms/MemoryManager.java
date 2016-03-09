package ca.concordia.adbms;

import java.io.FileInputStream;
import java.io.IOException;

import ca.concordia.adbms.conf.Configuration;

public class MemoryManager {
	
	
	private long timer = 0;
	
	private int resultSize = 0;
	private long tableFileSize = -1; 
	
	private Integer incomes = -1;
	private Integer people = -1; 
	
	private long execution = -1;
	
	public int getResultSize() {
		return resultSize;
	}
	
	
	public void startTimer(){
		 timer = System.currentTimeMillis();
	}
	
	public void stopTimer(){
		timer = System.currentTimeMillis() - timer;
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
	
	
	public double average(){
		//return Math.round((incomes/people) * 100)/100;
		return (double)(incomes/people); 
	}
	
	public void reportIncome(Integer income){
		if( incomes < 0 ) incomes = 0;
		if( people < 0 ) people = 0;
		incomes = incomes + income;
		people = people + 1;
	}
	
	/**
	 * Function to re-initialize variables
	 */
	public void reset(){
		timer = 0; 
		
		incomes = -1; 
		people = -1; 
		
		resultSize = 0; 
		tableFileSize = -1;
	}
	
	public void report(){
		String _report = String.format(" ====== Report ==== \r\n Execution time %.4f milliseconds. \r\n Average Income $ %.2f.\r\n Results %d ", timer, average(), resultSize);
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
