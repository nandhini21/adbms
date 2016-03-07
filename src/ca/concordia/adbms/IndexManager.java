package ca.concordia.adbms;

import java.util.ArrayList;



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
	 */
	public int[] getIndexKeys(int key) {
		//ArrayList tmp = new ArrayList();
		//while (true) {
		 //   tmp.add(0);
		//}
		//int[] values = tmp.toArray(new int[tmp.size()]);
		//return values;
		return new int[]{ 1 };
	}

	public void createIndex() {
		System.out.println("Implement Me");
	}
	
	

}
