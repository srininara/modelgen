package com.nacnez.util.modelgen.exampleModels;

import java.math.BigDecimal;

public class SimpleBigDecimalGenerationMockObject {
	
	private BigDecimal mockBothLimitBigDecimal;
	private BigDecimal mockNegativeBigDecimal;
	private BigDecimal mockDiffScaleBigDecimal;

	public BigDecimal getMockDiffScaleBigDecimal() {
		return mockDiffScaleBigDecimal;
	}
	
	public void setMockDiffScaleBigDecimal(BigDecimal mockDiffScaleBigDecimal) {
		this.mockDiffScaleBigDecimal = mockDiffScaleBigDecimal;
	}

	public BigDecimal getMockBothLimitBigDecimal() {
		return mockBothLimitBigDecimal;
	}
	public void setMockBothLimitBigDecimal(BigDecimal mockBothLimitBigDecimal) {
		this.mockBothLimitBigDecimal = mockBothLimitBigDecimal;
	}
	public BigDecimal getMockNegativeBigDecimal() {
		return mockNegativeBigDecimal;
	}
	public void setMockNegativeBigDecimal(BigDecimal mockNegativeBigDecimal) {
		this.mockNegativeBigDecimal = mockNegativeBigDecimal;
	} 
	
}
