package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomDateGenerator;

public class BasicDateGenerator extends BaseGenerator implements Generator {

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
