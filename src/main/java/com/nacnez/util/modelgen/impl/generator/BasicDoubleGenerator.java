package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomDoubleGenerator;

public class BasicDoubleGenerator extends BaseGenerator implements Generator {
	
	@Inject
	private RandomDoubleGenerator rdg;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		return rdg.generate();
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return true;
	}

}
