package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public class BasicStringGenerator extends StringGenerator implements Generator {

	RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl()); // DI required
	
	@Override
	protected Object doGenerate(List<Annotation> constraints) {
		return rsg.generate();
	}
	
	@Override
	protected boolean applicable(List<Annotation> constraints) {
		return (constraints==null || constraints.size()==0);
	}

}
