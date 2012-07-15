package com.nacnez.util.modelgen.impl.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.generator.BasicStringGenerator;
import com.nacnez.util.modelgen.impl.generator.Generator;
import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;
import com.nacnez.util.modelgen.impl.generator.SizedStringDecorator;
import com.nacnez.util.modelgen.impl.generator.StringGenerator;
import com.nacnez.util.modelgen.impl.generator.impl.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.impl.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;

public class ContractDigestImpl implements ContractDigest {

	@Inject
	private Mirror mirror; 

	// Consider changing this into its own domain object like below
	private Map<Method,Generator> methodToGeneratorMapping = new HashMap<Method,Generator>();
	
	@Inject @Named("typeGenMap")
	private TypeToGeneratorMapping typeToGeneratorMapping;

	private Class<? extends GenerationContract> contract;
	
	
	
	private List<Annotation> getConstraints(Class<?> clazz, String methodName) {
		Class<?> parameterType = getParameterType(clazz,methodName);
		List<Annotation> constraints = mirror.on(clazz).reflectAll().annotations().atMethod(methodName).withArgs(parameterType);

		if (constraints==null || constraints.size()==0) {
			Method m = mirror.on(clazz).reflect().method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}
	
	private Class<?> getParameterType(Class<?> clazz, String methodName) {
		return mirror.on(clazz).reflect().method(methodName).withAnyArgs().getParameterTypes()[0];
	}

	
	public void fill(Object model) {
		List<Method> methods = mirror.on(model.getClass()).reflectAll().methods();
		for (Method method: methods) {
			//find the contract method
			Method contractMethod = mirror.on(contract).reflect().method(method.getName()).withAnyArgs();
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
		List<Method> methods = mirror.on(contract).reflectAll().setters(); // methods() - Just supporting setters only
		for (Method method: methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			methodToGeneratorMapping.put(method,typeToGeneratorMapping.get(parameterTypes[0]));
		}
		return this;
	}

}


