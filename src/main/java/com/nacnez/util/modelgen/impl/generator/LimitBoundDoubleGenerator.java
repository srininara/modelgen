package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.DoubleLimit;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomDoubleGenerator;

public class LimitBoundDoubleGenerator extends BaseGenerator implements Generator {

	@Inject
	private RandomDoubleGenerator rdg;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		DoubleLimit limit = (DoubleLimit) constraintList.get(DoubleLimit.class);
		Double lowLimit = new Double(limit.lowLimit());
		Double highLimit = new Double(limit.highLimit());
		return rdg.generate(lowLimit, highLimit);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(DoubleLimit.class);
	}

}
