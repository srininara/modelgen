package com.nacnez.util.modelgen.impl.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.BasicStringGenerator;
import com.nacnez.util.modelgen.impl.generator.Generator;
import com.nacnez.util.modelgen.impl.generator.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;
import com.nacnez.util.modelgen.impl.generator.SizedStringGenerator;

public class ContractDigestImpl implements ContractDigest {


	private Mirror mirror = new Mirror(); // Can be a singleton I think

	// TODO - Might want to change the ordinary object above to a generic Generator object
	private Map<Method,Generator> methodToGeneratorMapping = new HashMap<Method,Generator>(); 

	private Map<Class<?>,Generator> typeToGeneratorMapping = new HashMap<Class<?>,Generator>(); // TODO - same as above
	
	private Class<? extends GenerationContract> contract;
	
	
	public ContractDigestImpl() {
		this.init();
	}
	
	private void init() {
		// Might want to initialize this through a DI container may be.
		typeToGeneratorMapping.put(String.class, getStringGenerator());
//		typeToGeneratorMapping.put(String.class, new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl()));
//		typeToGeneratorMapping.put(Integer.class, new JavaUtilRandomIntegerGeneratorImpl());
	}
			
	private Generator getStringGenerator() {
		RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl());
		Generator basicStringGenerator = new BasicStringGenerator(); // NOTE: Might be worthwhile to support an extra constructor as well
		basicStringGenerator.setNext(new SizedStringGenerator());
		return basicStringGenerator;
	}
	
	private List<Annotation> getConstraints(Class<?> clazz, String methodName) {
		Class<?> parameterType = getParameterType(clazz,methodName);
		return mirror.on(clazz).reflectAll().annotations().atMethod(methodName).withArgs(parameterType);
	}
	
	private Class<?> getParameterType(Class<?> clazz, String methodName) {
		return mirror.on(clazz).reflect().method(methodName).withAnyArgs().getParameterTypes()[0];
	}

	
	public void fill(Object model) {
		List<Method> methods = mirror.on(model.getClass()).reflectAll().methods();
		for (Method method: methods) {
			//find the contract method
			Method contractMethod = new Mirror().on(contract).reflect().method(method.getName()).withAnyArgs();
			Generator generator = methodToGeneratorMapping.get(contractMethod);
			
			if (generator != null) {
				mirror.on(model).invoke().method(method).withArgs(generator.generate(getConstraints(contract, method.getName())));
			}
		}
	}

	public String info() {
		return null;
	}
	
	public ContractDigest digest(Class<? extends GenerationContract> contract) {
		this.contract = contract;
		// A null check on the contract is probably useful
		List<Method> methods = mirror.on(contract).reflectAll().methods();
		for (Method method: methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			//TODO - Going to assume that there is only one parameter
			// TODO - Also going to assume that the parameter type is String
			methodToGeneratorMapping.put(method,typeToGeneratorMapping.get(parameterTypes[0]));
		}
		return this;
	}

}


//List<Method> methods = mirror.on(contract).reflectAll().methods();
////CompositeContractDigest<T> wrapperDigest = createCompositeContractDigest();
//for (Method method: methods) {
//Class<?>[] parameterTypes = method.getParameterTypes();
//
//}
