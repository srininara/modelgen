package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.TargetModel;
import com.nacnez.util.modelgen.generator.rules.Limit;

@TargetModel(modelClass=SimpleIntegerGenerationMockObject.class)
public interface SimpleIntegerGenerationMockContract extends GenerationContract {

	
	void setMockNoLimitInteger(Integer mockNoLimitInteger);
	
	@Limit
	void setMockDefaultLimitInteger(Integer mockDefaultLimitInteger);
	
	@Limit (highLimit=1000000)
	void setMockHighLimitInteger(Integer mockHighLimitInteger);
	
	@Limit (lowLimit=1000001)
	void setMockLowLimitInteger(Integer mockLowLimitInteger);	
	
	@Limit (lowLimit=10000, highLimit=20000)
	void setMockBothLimitInteger(Integer mockBothLimitInteger);	


	@Limit (highLimit=-1)
	void setMockNegativeInteger(Integer mockNegativeInteger);	

}
