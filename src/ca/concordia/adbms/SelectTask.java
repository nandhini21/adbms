package ca.concordia.adbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.concordia.adbms.conf.Configuration;
import ca.concordia.adbms.model.Person;
import ca.concordia.adbms.util.Parser;

/**
 * Bonjour Task is used by the client to establish connection with a given
 * remote server.
 * 
 * @author murindwaz
 */
public class SelectTask implements Task {
	// age to select from
	private int min;
	private int max;

	public SelectTask(String argument) throws IOException {
		String[] select = Parser.parseSelect(argument);
		min = Integer.parseInt(select[0]);
		max = Integer.parseInt(select[1]);
	}

	
	/**
	 * @todo --- add index in at this section. 
	 * @todo --- only read sequentially if there is no index 
	 * @todo --- build index on first read, or at initialization 
	 */
	public void execute() {
		File file = new File(Configuration.PERSON_FILE);
		FileInputStream rstream = null;
		Person person = null; 
		int reads = 0;
		try {
			// create FileInputStream object
			rstream = new FileInputStream(file);
			byte buffer[] = new byte[Configuration.TUPLE_SIZE];

			// create string from byte array
			// Reads up to certain bytes of data from this input stream into an
			// array of bytes.
			// rstream.read(buffer);
			/**
			 * public int read(byte[] b, int off, int len) throws IOException
			 * buffer - the buffer into which the data is read. off - the start
			 * offset in the destination array buffer len - the maximum number
			 * of bytes read.
			 */
			while ((reads = rstream.read(buffer, 0, buffer.length)) != -1) {
				person = Parser.parse(buffer);
				System.out.println(" +++ " + person.toString() + " reads:: " + reads + " length"
						+ buffer.length);
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