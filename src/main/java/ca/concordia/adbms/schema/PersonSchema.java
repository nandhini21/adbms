package ca.concordia.adbms.schema;

public class PersonSchema {

	public static final int TUPLE_SIZE = 100;
	public static final int SIN = 9;
	public static final int FIRST = 15;
	public static final int LAST = 15;
	public static final int AGE = 2;
	public static final int INCOME = 10;
	public static final int ADDRESS = 49;

	
	/**
	 * Location of data in a file 
	 */
	public static final int getSinOffset(){
		return 0;
	}
	
	public static final int getFirstNameOffset(){
		return getSinOffset() + SIN; 
	}

	public static final int getLastNameOffset(){
		return getFirstNameOffset() + FIRST;
	}
	
	public static final int getAgeOffset(){
		return getLastNameOffset() + LAST;
	}
	
	public static final int getIncomeOffset(){
		return getAgeOffset() + AGE;
	}
	
	public static final int getAddressOffset(){
		return getIncomeOffset() + INCOME;
	}
}