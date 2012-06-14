package com.nacnez.util.modelgen.exampleModels;


public class SimpleMockObject {

	private String mockSizedString;
	private String mockUnSizedString;
	private String mockSizedStringParam;
	
	public void setMockSizedString(String mockSizedString) {
		this.mockSizedString = mockSizedString;
	}

	public void setMockUnSizedString(String mockUnSizedString) {
		this.mockUnSizedString = mockUnSizedString;
	}

	public String getMockSizedString() {
		return mockSizedString;
	}

	public String getMockUnSizedString() {
		return mockUnSizedString;
	}
	
	public String getMockedSizedStringParam() {
		return mockSizedStringParam;
	}

	public void setMockSizedStringParam(String mockSizedStringParam) {
		this.mockSizedStringParam = mockSizedStringParam;
	}

}
