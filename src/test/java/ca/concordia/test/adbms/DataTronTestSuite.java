package ca.concordia.test.adbms; 


import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author murindwaz
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DataTronTest.class})
public class DataTronTestSuite {

	// helpful when running using JUnit 3 Test runners or Ant
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DataTronTestSuite.class);
	}

	// text test runner that tells if tests fails
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}