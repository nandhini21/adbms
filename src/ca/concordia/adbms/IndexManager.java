package ca.concordia.adbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.util.Parser;



/**
 * This class will be able to: 
 * 	- Initialize index on PERSON.txt file. 
 *  - Retrieve an index(key, value) pairs for a given key
 */
public class IndexManager {

	/**
	 * 
	 * @link Use of dynamic arrays http://stackoverflow.com/a/1647374/132610
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public Integer[] getIndexKeys(int key) throws IOException{
		String indexFileName = String.format("%s%d.txt", Configuration.INDEX_BASE_PATH, key);
		File file = new File(indexFileName);
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		if(file.exists()){
			byte [] buffer = new byte[(int) file.length()];//this will crash the file if file is bigger that available size 
			int read = new FileInputStream(file).read(buffer, 0, buffer.length); 
			//@todo --- read and parse bytes this cannot fit in available memory 
			Integer[] indexLines = Parser.parseIndexPointer(new String(buffer) );
			for(Integer lineNumber: indexLines ){
				tmp.add(lineNumber);
			}
			buffer = null;//clear the address asap
		}
		Integer[] values = tmp.toArray(new Integer[tmp.size()]);
		tmp = null; 
		return values;
	}

	
	/**
	 * @todo Make sure we create the index Once 
	 * @todo build index here Index looks like: INDEX = [ age[18] : [l1,l3,
	 *       l10], age[20] : [l12,l13, l110] age[60] : [l21,l23, l210] ]
	 * @todo - Keep it in memory
	 * @todo - Flush it to files if cannot fit in memory DONE building index
	 */
	public void createIndex(FileInputStream fis){
		int read;
		int ios = 0;
		int keyValueLineNumber = 0;
		
		Person person = null;//Person model
		String indexFileLocation;//string to file system
		Hashtable<Integer, FileOutputStream> indexHashTable = new Hashtable<Integer, FileOutputStream>();
		//byte buffer[] = new byte[Configuration.BLOCK_SIZE];
		byte buffer[] = new byte[Configuration.TUPLE_SIZE];//is 100B
		try {
			System.out.println("Creating the index");
			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				ios += 1;
				// number of records read
				//int numRecords = (int) Math.floor(read / Configuration.TUPLE_SIZE);
				//for (int i = 0; i < numRecords; i++) {
					person = Parser.parse(buffer, 0);
					//this does not prevent file from being created for second time 
					indexFileLocation = String.format("%s%d.txt", Configuration.INDEX_BASE_PATH, person.getAge());
					if (!indexHashTable.containsKey(person.getAge())) { 
						indexHashTable.put(person.getAge(), new FileOutputStream(indexFileLocation, true));
					}
					//parsable filewrite line to index
					indexHashTable.get(person.getAge()).write(String.format("%s ", keyValueLineNumber).getBytes());
					keyValueLineNumber++;
			}
			System.out.println(String.format("Index creation complete, lines: %d  num of ios: %d", ios, keyValueLineNumber) );
		} catch (Exception exception) {
			//FileNotFoundException - IOException - Or Exception are 
			System.out.println( String.format( "Exception. Code %s - details %s", exception.getClass().getName(), exception.getMessage()));
		} finally {
			try {
				// close the streams using close method --- this may be an Issue, if shared stream is being used
				if (fis != null) {
					fis.close();
				}
				for (FileOutputStream stream : indexHashTable.values()) {
					stream.close();// Closing the index files
				}
				indexHashTable = null;//clears data type to reduce memory footprint
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
	}
	
	

}
