package ca.concordia.adbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
	private IndexManager indexManager;
	private Integer[] indexValueLineNumbers;

	// injecting memory manager in this task
	public void setMemoryManager(MemoryManager memoryManager) {
		this.memoryManager = memoryManager;
	}


	public MemoryManager getMemoryManager() {
		return memoryManager;
	}

	
	public void setIndexManager(IndexManager indexManager) {
		this.indexManager = indexManager;
	}
	public IndexManager getIndexManager(){
		return indexManager; 
	}
	
	private File file = null;
	private FileChannel channel;
	private FileInputStream rstream = null;

	
	
	public SelectTask(String argument) throws IOException {
		file = new File(Configuration.PERSON_FILE);
		rstream = new FileInputStream(file);
		channel = rstream.getChannel();
		query = Parser.parseSelect(argument);
	}

	public void execute() throws ExitException {
		int reads = 0;
		Person person = null;
		try {
			indexValueLineNumbers = indexManager.getIndexKeys(query.getAge());
			memoryManager.calculateFileReadPass(rstream);
			// Array Size should always be smaller than Integer.MAX_VALUE
			byte buffer[] = new byte[Configuration.TUPLE_SIZE];//is 100KB
			for(Integer lineNumber: indexValueLineNumbers){
				ByteBuffer bbuffer = ByteBuffer.wrap(buffer); //[Configuration.TUPLE_SIZE]; 
				reads = (int)rstream.getChannel().position(lineNumber  * Configuration.TUPLE_SIZE).read(bbuffer, Configuration.TUPLE_SIZE );//, Configuration.TUPLE_SIZE 
				if( reads > -1 ){
					person = Parser.parse(bbuffer, 0);
					if (query.getAge() > -1 && person.getAge() == query.getAge()) {
						memoryManager.increment();
							System.out.println(String.format(" %s ", person.toString()));
					} else if (query.getMax() > -1 && query.getMin() > -1) {
						if (person.getAge() <= query.getMax() && person.getAge() >= query.getMin()) {
							memoryManager.increment();
							System.out.println(String.format(" %s ", person.toString()));
						}
					}
				}
			}
		} catch (Exception exception) {
			//FileNotFoundException - IOException - Or Exception are 
			System.out.println( String.format( "Exception. Code %s - details %s", exception.getClass().getName(), exception.getMessage()));
		}
	}



}