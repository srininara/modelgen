package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.generator.rules.Size;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomStringGenerator;

public class AlphabeticStringGenerator extends BaseGenerator implements Generator {

	@Inject
	private RandomStringGenerator rsg;
	
	public static final int DEFAULT_LENGTH = 32;

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(Alphabetic.class);
	}

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		return rsg.generateAlphabetic(getLength(constraintList));
	}

	protected int getLength(ConstraintList constraintList) {
		if (constraintList.contains(Size.class)) {
			return ((Size) constraintList.get(Size.class)).maxSize();
		}
		return DEFAULT_LENGTH;
	}
	

	
	
}
