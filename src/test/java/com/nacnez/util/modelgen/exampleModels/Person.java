package com.nacnez.util.modelgen.exampleModels;



public class Person {

	private String creditCardNumber;
	
	private String firstName;
	
	private String PAN;
	
	private String maritalStatus;
	
	private Integer id;
	
	private Integer creditAmount;

	private Integer loanAmount;
	
	private String mockSizedStringParam;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreditAmount() {
		return creditAmount;
	}
	
	public void setCreditAmount(Integer creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}
	
	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	public String getMockedSizedStringParam() {
		return mockSizedStringParam;
	}

	public void setMockSizedStringParam(String mockSizedStringParam) {
		this.mockSizedStringParam = mockSizedStringParam;
	}

}


