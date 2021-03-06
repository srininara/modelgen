package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomBigDecimalGenerator;

public class BasicBigDecimalGenerator extends BaseGenerator implements Generator {
	
	@Inject
	private RandomBigDecimalGenerator rbdg;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		return rbdg.generate();
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return true;
	}

}
