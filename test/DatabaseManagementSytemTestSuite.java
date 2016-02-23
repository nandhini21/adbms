

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author murindwaz
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DatabaseManagementSystem.class})
public class DatabaseManagementSytemTestSuite {

	// helpful when running using JUnit 3 Test runners or Ant
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DatabaseManagementSystem.class);
	}

	// text test runner that tells if tests fails
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}