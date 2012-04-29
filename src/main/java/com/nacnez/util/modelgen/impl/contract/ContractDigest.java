package com.nacnez.util.modelgen.impl.contract;

import java.util.Collection;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.DataGenerator;

public interface ContractDigest<T> {
	void fill(T model);
	String info();
	ContractDigest<T> digest(Class<? extends GenerationContract> contract);
}
