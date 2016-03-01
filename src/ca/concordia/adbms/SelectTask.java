package ca.concordia.adbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.util.Parser;

/**
 * Select Task executes search query.
 */
public class SelectTask implements Task {
	
	// age to select from
	private SelectQuery query; 
	private MemoryManager memoryManager; 
	
	//injecting memory manager in this task 
	public void setMemoryManager(MemoryManager memoryManager){
		this.memoryManager = memoryManager; 
	}
	public MemoryManager getMemoryManager(){
		return memoryManager;
	}
	
	
	
	public SelectTask(String argument) throws IOException {
		query = Parser.parseSelect(argument);
	}

	
	/**
	 * @todo --- add index in at this section. 
	 * @todo --- only read sequentially if there is no index 
	 * @todo --- build index on first read, or at initialization 
	 */
	public void execute() throws ExitException {
		File file = new File(Configuration.PERSON_FILE);
		FileInputStream rstream = null;
		Person person = null; 
		int reads = 0;
		try {
			// create FileInputStream object
			rstream = new FileInputStream(file);
			byte buffer[] = new byte[Configuration.TUPLE_SIZE];
			/**
			 * public int read(byte[] b, int off, int len) throws IOException
			 * buffer - the buffer into which the data is read. off - the start
			 * offset in the destination array buffer len - the maximum number
			 * of bytes read.
			 */
			while ((reads = rstream.read(buffer, 0, buffer.length)) != -1) {
				person = Parser.parse(buffer);
				if( query.getAge() > -1 && person.getAge() == query.getAge() ){ 
					memoryManager.increment();
					System.out.println(String.format(" %s ", person.toString()));
				} else if( query.getMax() > -1 && query.getMin() > -1 ){
					memoryManager.increment();
					System.out.println(String.format(" %s ", person.toString()));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading file " + ioe);
		} finally {
			// close the streams using close method
			try {
				if (rstream != null) {
					rstream.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
	}

}