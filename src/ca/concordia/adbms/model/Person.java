package ca.concordia.adbms.model;


public class Person {
	
	
	
	//9 bytes String 
	private String sin; 
	//15 bytes String 
	private String firstName; 
	//15 bytes String
	private String lastName; 
	//2 bytes Integer ... a 16 Bits processor has 2 bytes integer, but not a 64 Bits processor  
	private Integer age; 
	//10 bytes => Decimal 10.2 
	private Integer income; 
	//49 bytes 
	private String address;
	
	
	
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	} 

	//@todo add how a person looks in a too string setting
	public String toString(){
		return String.format("SIN %s FIRST %s LAST %s AGE %d INCOME %d ADDRESS %s", getSin(), getFirstName(), getLastName(), getAge(), getIncome(), getAddress() );
	}
	
	

}