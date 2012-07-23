package com.nacnez.util.modelgen.exampleModels;

public class CompoundMockObject {
	
	private String compoundId;
	
	private SimpleMockObject smo;
	
	private SimpleIntegerGenerationMockObject sigmo;

	public String getCompoundId() {
		return compoundId;
	}

	public void setCompoundId(String compoundId) {
		this.compoundId = compoundId;
	}

	public SimpleMockObject getSmo() {
		return smo;
	}

	public void setSmo(SimpleMockObject smo) {
		this.smo = smo;
	}

	public SimpleIntegerGenerationMockObject getSigmo() {
		return sigmo;
	}

	public void setSigmo(SimpleIntegerGenerationMockObject sigmo) {
		this.sigmo = sigmo;
	}
	
	

}
