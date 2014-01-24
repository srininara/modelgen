package com.nacnez.util.modelgen.impl.contract;

import com.nacnez.util.modelgen.GenerationContract;

public interface ContractDigest {
	ContractDigest digest(Class<? extends GenerationContract> contract);
	Object make();
}
