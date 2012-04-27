package com.nacnez.util.modelgen.impl.contract;

import com.nacnez.util.modelgen.GenerationContract;

public interface ContractDigester<T> {
	ContractDigest<T> digest(Class<? extends GenerationContract> contract);
}
