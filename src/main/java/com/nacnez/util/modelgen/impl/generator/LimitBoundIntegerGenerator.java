package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.Limit;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomIntegerGenerator;

public class LimitBoundIntegerGenerator extends BaseGenerator implements Generator {

	@Inject
	private RandomIntegerGenerator rig;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		Limit limit = (Limit) constraintList.get(Limit.class);
		int lowLimit = limit.lowLimit();
		int highLimit = limit.highLimit();
		return rig.generate(lowLimit, highLimit);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(Limit.class);
	}

}
