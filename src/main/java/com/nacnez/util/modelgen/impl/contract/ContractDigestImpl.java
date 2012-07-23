package com.nacnez.util.modelgen.impl.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vidageek.mirror.dsl.Mirror;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.Generator;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;

public class ContractDigestImpl implements ContractDigest {

	@Inject
	private Mirror mirror;

	// Consider changing this into its own domain object like below
	private Map<Method, Generator> methodToGeneratorMapping = new HashMap<Method, Generator>();

	@Inject
	@Named("typeGenMap")
	private TypeToGeneratorMapping typeToGeneratorMapping;

	private Class<? extends GenerationContract> contract;
	
	@Inject
	private ContractDigestFactory factory;

	private List<Annotation> getConstraints(Class<?> clazz, String methodName) {
		Class<?> parameterType = getParameterType(clazz, methodName);
		List<Annotation> constraints = mirror.on(clazz).reflectAll()
				.annotations().atMethod(methodName).withArgs(parameterType);

		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(clazz).reflect().method(methodName)
					.withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}

	private List<Annotation> getConstraints(Method method) {
		Annotation[] annotations = method.getAnnotations();

		if (annotations != null) {
			return Arrays.asList(annotations);
		}
		annotations = method.getParameterAnnotations()[0];

		if (annotations != null) {
			return Arrays.asList(annotations);
		}

		return new ArrayList<Annotation>();

	}

	private Class<?> getParameterType(Class<?> clazz, String methodName) {
		return mirror.on(clazz).reflect().method(methodName).withAnyArgs()
				.getParameterTypes()[0];
	}

	public Object make() {
		Object model = createModel();
		fill(model);
		return model;
	}

	private Object createModel() {
		Class modelClass = findTargetModel();
		try {
			return modelClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Problem occured", e);
		}
	}

	private Class findTargetModel() {
		List<Annotation> annotations = mirror.on(contract).reflectAll()
				.annotations().atClass();
		TargetModel target = (TargetModel) annotations.get(0);
		// Assumption is that there would be only one annotation at
		// the class level on the contract

		return target.modelClass();
	}

	private void fill(Object model) {
		List<Method> methods = mirror.on(model.getClass()).reflectAll()
				.methods();
		for (Method method : methods) {
			// find the contract method
			Method contractMethod = mirror.on(contract).reflect()
					.method(method.getName()).withAnyArgs();
			if (contractMethod != null) {
				Generator generator = methodToGeneratorMapping
						.get(contractMethod);
				if (generator != null) {
					Object generatedValue = generator.generate(getConstraints(
							contract, method.getName()));
					mirror.on(model).invoke().method(method)
							.withArgs(generatedValue);
				}
			}
		}
	}

	public ContractDigest digest(Class<? extends GenerationContract> contract) {
		this.contract = contract;
		// A null check on the contract is probably useful
		List<Method> methods = mirror.on(contract).reflectAll().setters();
		// Just supporting setters only for now
		for (Method method : methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			Generator generator = typeToGeneratorMapping.get(parameterTypes[0]);
			if (generator != null) {
				methodToGeneratorMapping.put(method, generator);
			} else {
				digestInnerModel(method);
			}

		}
		return this;
	}

	private void digestInnerModel(Method method) {
		List<Annotation> annotations = getConstraints(method);
		if (annotations.size() > 0
				&& annotations.get(0).annotationType().equals(Contract.class)) {
			final ContractDigest innerOne = createInnerContractDigest();
			innerOne.digest(((Contract) annotations.get(0)).contractClass());
			Generator innerContractGen = new Generator() {

				public Object generate(List<Annotation> constraints) {
					return innerOne.make();
				}

				public void setNext(Generator next) {
					// Do nothing
				}
			};
			methodToGeneratorMapping.put(method, innerContractGen);
		}
	}

	private ContractDigest createInnerContractDigest() {
		return factory.createContractDigest();
	}

}
