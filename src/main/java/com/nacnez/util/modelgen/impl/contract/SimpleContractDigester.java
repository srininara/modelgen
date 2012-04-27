package com.nacnez.util.modelgen.impl.contract;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import com.nacnez.util.modelgen.GenerationContract;

public class SimpleContractDigester<T> implements ContractDigester<T> {
	
	private Mirror mirror = new Mirror();
	
	public ContractDigest<T> digest(Class<? extends GenerationContract> contract) {
		List<Method> methods = mirror.on(contract).reflectAll().methods();
		CompositeContractDigest<T> wrapperDigest = createCompositeContractDigest();
		for (Method method: methods) {
			SimpleContractDigest<T> digest = createSimpleContractDigest();
			digest.set(method);
			wrapperDigest.add(digest);
		}
		return wrapperDigest;
	}
	
	private SimpleContractDigest<T> createSimpleContractDigest() {
		return new SimpleContractDigest<T>();
	}
	
	private CompositeContractDigest<T> createCompositeContractDigest() {
		return new CompositeContractDigest<T>();
	}

}
