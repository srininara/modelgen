package com.nacnez.util.modelgen.impl.contract;

import com.nacnez.util.modelgen.GenerationContract;

public interface ContractDigest {
	void fill(Object model);
	String info();
	ContractDigest digest(Class<? extends GenerationContract> contract);
}
