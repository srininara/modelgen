package com.nacnez.util.modelgen.impl.contract;

import java.util.Collection;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.DataGenerator;

public interface ContractDigest {
	void fill(Object model);
	String info();
	ContractDigest digest(Class<? extends GenerationContract> contract);
}
