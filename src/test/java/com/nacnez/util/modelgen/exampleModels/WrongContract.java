package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.impl.generator.rules.Limit;

public interface WrongContract {

	@Limit(highLimit=1000000, lowLimit = 500)
	void setId(Integer id);

	@Limit(highLimit=1000000, lowLimit = -500)
	void setAnotherId(Integer anotherId);

	@Limit(highLimit=-1000000, lowLimit = 0)
	void setYetAnotherId(Integer anotherId);
	
//	@Negative
	@Limit(highLimit=-100, lowLimit = -100000)
	void setAnotherLoanAmount(Integer anotherLoanAmount);
	
	
//	@Negative
	@Limit(highLimit=0, lowLimit = 100)
	void setAnotherAmount(Integer anotherAmount);
	
//	@Negative
//	@Limit(highLimit=100, lowLimit = 0)
//	void setYetAnotherAmount(Integer anotherAmount);
}
