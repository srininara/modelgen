package com.nacnez.util.modelgen.exampleModels;

import java.util.Date;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.TargetModel;
import com.nacnez.util.modelgen.generator.rules.DateLimit;

@TargetModel(modelClass=SimpleDateGenerationMockObject.class)
public interface SimpleDateGenerationMockContract extends GenerationContract {

	@DateLimit(lowLimit="15-01-2010",highLimit="15-01-2012")
	void setMockBothLimitDate(Date mockBothLimitDate);

	@DateLimit(lowLimit="01-Jan-2010",highLimit="01-Jan-2012",format="dd-MMM-yyyy")
	void setMockDiffFormatDate(Date mockDiffScaleDate);

}