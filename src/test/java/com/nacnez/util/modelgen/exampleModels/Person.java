package com.nacnez.util.modelgen.exampleModels;


public class Person {

	private String creditCardNumber;
	
	private String firstName;
	
	private String PAN;
	
	private String maritalStatus;
	
	void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	void setPAN(String PAN) {
		this.PAN = PAN;
	}
	
	void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPAN() {
		return PAN;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}
}


