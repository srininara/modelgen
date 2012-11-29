package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomIntegerGenerator;

public class BasicIntegerGenerator extends IntegerGenerator implements Generator {

	@Inject
	private RandomIntegerGenerator rig;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		return rig.generate();
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return true;
	}

}
