package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public interface SimpleMockGenerationContract extends GenerationContract {

	@Size(maxSize=32)
	void setMockSizedString(String mockSizedString);
	
	void setMockUnSizedString(String mockUnSizedString);
	
	void setMockSizedStringParam(@Size(maxSize=50) String mockSizedStringParam);
	
}
