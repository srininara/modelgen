package com.nacnez.util.modelgen.impl.generator.action;

import com.nacnez.util.modelgen.impl.generator.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;

public class SizedStringGenerationAction implements GenerationAction {

	RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl());
	
	
	public SizedStringGenerationAction(int size) {
		
	}
	public Object generate() {
		return rsg.generate();
	}

}
