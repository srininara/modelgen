package com.nacnez.util.modelgen.impl.contract;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.vidageek.mirror.dsl.Mirror;

import com.nacnez.util.modelgen.impl.generator.DataGenerator;

public class SimpleContractDigest<T> implements ContractDigest<T> {

	private Method method;
	
	private DataGenerator<T> generator;
	
	private Mirror mirror = new Mirror();

	//TODO - Refactor/Redesign this to make it more generalized
	//TODO - Figure out 
	public void fill(T model) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		if(String.class.equals(parameterTypes[0])) {
			Method methodOnTarget = new Mirror().on(model.getClass()).reflect().method(method.getName()).withArgs(parameterTypes[0]);
			T generatedOutput = generator.initToProvide().randomData().generate();
			mirror.on(model).invoke().method(methodOnTarget)
            .withArgs(generatedOutput);
		}
	}

	public String info() {
		return method.toString();
	}
	
	void set(Method method) {
		this.method = method;
	}

	public void setGenerator(DataGenerator dataGenerator) {
		this.generator = dataGenerator;
	}

}
