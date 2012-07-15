package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;

public class AlphabeticStringGenerator extends StringGenerator implements Generator {

	@Inject
	private RandomStringGenerator rsg;
	
	@Override
	protected Object doGenerate(ConstraintList constraintList, int size) {
		return rsg.generateAlphabetic(size);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(Alphabetic.class);
	}

}
