package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public class BasicStringGenerator implements Generator {

	RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl()); // DI required
	private Generator next;
	
	public Object generate(List<Annotation> constraints) {
		if (constraints==null) {
			return rsg.generate();
		}
		if (next!=null) {
			return next.generate(constraints);
		}
		return null;
	}

	public void setNext(Generator next) {
		this.next = next;
	}

}
