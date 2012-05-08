package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.impl.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public interface PersonContract extends GenerationContract {

	@Size(maxSize=15)
	void setCreditCardNumber(String creditCardNumber);
	
	@Size(maxSize=32)
	@Alphabetic
	void setFirstName(String name);
	
	@Alphanumeric
	@Size(maxSize=15)
	void setPAN(String pan);
	
	@FromList(fromList={"Single","Married","Divorced"})
	void setMaritalStatus(String maritalStatus);
}