package com.nacnez.util.modelgen.exampleModels;

import java.math.BigDecimal;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.TargetModel;
import com.nacnez.util.modelgen.generator.rules.DecimalLimit;

@TargetModel(modelClass=SimpleBigDecimalGenerationMockObject.class)
public interface SimpleBigDecimalGenerationMockContract extends GenerationContract {

	public void setMockNoLimitBigDecimal(BigDecimal mockNoLimitBigDecimal); 
	
	
	@DecimalLimit(lowLimit="20000002.22",highLimit="40000002.43")
	public void setMockBothLimitBigDecimal(BigDecimal mockBothLimitBigDecimal);
	
	@DecimalLimit(lowLimit="-20000002.22",highLimit="-1.12")
	public void setMockNegativeBigDecimal(BigDecimal mockNegativeBigDecimal);
	
	@DecimalLimit(lowLimit="10000.123",highLimit="40000002.43",scale=3)
	public void setMockDiffScaleBigDecimal(BigDecimal mockDiffPrecisionBigDecimal);

}