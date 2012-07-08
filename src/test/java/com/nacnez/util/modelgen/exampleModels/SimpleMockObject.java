package com.nacnez.util.modelgen.exampleModels;


public class SimpleMockObject {

	private String mockSizedString;
	private String mockUnSizedString;
	private String mockSizedStringParam;
	private String mockAlphabeticString;
	
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
	
	public void setMockAlphabeticString(String mockAlphabeticString){
		this.mockAlphabeticString = mockAlphabeticString;
	}

	public String getMockAlphabeticString(String mockAlphabeticString){
		return mockAlphabeticString;
	}

}
