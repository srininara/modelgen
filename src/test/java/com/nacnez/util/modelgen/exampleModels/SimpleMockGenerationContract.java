package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.impl.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public interface SimpleMockGenerationContract extends GenerationContract {

	@Size(maxSize=32)
	void setMockSizedString(String mockSizedString);
	
	void setMockUnSizedString(String mockUnSizedString);
	
	void setMockSizedStringParam(@Size(maxSize=50) String mockSizedStringParam);
	
	@Alphabetic
	void setMockAlphabeticString(String mockAlphabeticString);
	
	@Alphabetic
	@Size(maxSize=40)
	void setMockAlphabeticSizedString(String mockAlphabeticSizedString);

	@Alphanumeric
	void setMockAlphanumericString(String mockAlphanumericString);
	
	@Alphanumeric
	@Size(maxSize=40)
	void setMockSizedAlphanumericString(String mockSizedAlphanumericString);

	@FromList(fromList={"Mock1","Mock2","Mock3"})
	void setMockStringFromList(String mockStringFromList);

}
