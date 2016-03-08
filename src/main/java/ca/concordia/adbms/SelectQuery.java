package ca.concordia.adbms;

/**
 * This function will be used to express Select Query  
 */
public class SelectQuery {
	
	private int min;
	private int age;
	private int max;

	public SelectQuery( int min, int age, int max){
		this.min = min; 
		this.age = age; 
		this.max = max; 
	}
	
	public int getMin(){ return min; }
	public int getMax(){ return max; }
	public int getAge(){ return age; }

}