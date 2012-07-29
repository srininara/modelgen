package com.nacnez.util.modelgen.impl.contract;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;

public class ContractDigestFactory {
	
	ContractDigest createContractDigest() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		return injector.getInstance(ContractDigest.class);
	}

}
