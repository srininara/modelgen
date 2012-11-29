package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomStringGenerator;

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
