package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;;

public class AlphanumericStringGenerator extends StringGenerator implements Generator {
	@Inject
	private RandomStringGenerator rsg;
	
	@Override
	protected Object doGenerate(ConstraintList constraintList, int size) {
		return rsg.generateAlphaNumeric(size);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(Alphanumeric.class);
	}

}
