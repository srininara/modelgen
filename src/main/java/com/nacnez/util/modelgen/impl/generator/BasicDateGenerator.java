package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class BasicDateGenerator extends DateGenerator implements Generator {

	@Inject
	private RandomDateGenerator rdg;

	
	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		return rdg.generate();
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return true;
	}
	

}
