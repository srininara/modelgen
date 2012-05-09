package com.nacnez.util.modelgen.impl.contract;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.DataGenerator;

public class ContractDigestImpl<T> implements ContractDigest<T> {


	private Mirror mirror = new Mirror();

	public void fill(T model) {
	}

	public String info() {
		return null;
	}
	
	
	public ContractDigest<T> digest(Class<? extends GenerationContract> contract) {
//		List<Method> methods = mirror.on(contract).reflectAll().methods();
////		CompositeContractDigest<T> wrapperDigest = createCompositeContractDigest();
//		for (Method method: methods) {
//			Class<?>[] parameterTypes = method.getParameterTypes();
//
//		}
		return this;
	}

}
