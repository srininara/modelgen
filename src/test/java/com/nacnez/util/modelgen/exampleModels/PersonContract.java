package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;

public interface PersonContract extends GenerationContract {

	void setName(String name);
	
	void setNickName(String nickName);

}