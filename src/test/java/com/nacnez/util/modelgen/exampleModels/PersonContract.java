package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public interface PersonContract extends GenerationContract {

	@Size(maxSize=50)
	void setName(String name);
	
	void setNickName(String nickName);

}