package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.TargetModel;
import com.nacnez.util.modelgen.generator.rules.DoubleLimit;

@TargetModel(modelClass=SimpleDoubleGenerationMockObject.class)
public interface SimpleDoubleGenerationMockContract extends GenerationContract {

	public void setMockNoLimitDouble(Double mockNoLimitDouble); 
	
	@DoubleLimit(lowLimit="20000002.22",highLimit="40000002.43")
	public void setMockBothLimitDouble(Double mockBothLimitDouble);
	
	@DoubleLimit(lowLimit="-20000002.22",highLimit="-1.12")
	public void setMockNegativeDouble(Double mockNegativeDouble);
	
}