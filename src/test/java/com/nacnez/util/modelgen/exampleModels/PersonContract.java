package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.generator.rules.FromList;
import com.nacnez.util.modelgen.generator.rules.Limit;
import com.nacnez.util.modelgen.generator.rules.Size;

public interface PersonContract extends GenerationContract {

	@Size(maxSize=15)
	void setCreditCardNumber(String creditCardNumber);
	
	@Size(maxSize=35)
	@Alphabetic
	void setFirstName(String firstName);
	
	@Alphanumeric
	@Size(maxSize=15)
	void setPAN(String PAN);
	
	@FromList(fromList={"Single","Married","Divorced"})
	void setMaritalStatus(String maritalStatus);
	
	@Limit(highLimit=1000000, lowLimit = 0)
	void setId(Integer id);
	
	@Limit(highLimit=0)
	void setCreditAmount(Integer creditAmount);
	
	@Limit(lowLimit=-1000000, highLimit = 0)
	void setLoanAmount(Integer id);
	
	void setMockSizedStringParam(@Size(maxSize=32) String mockSizedStringParam);
	
}