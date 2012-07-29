package com.nacnez.util.modelgen.impl.generator;

import java.math.BigDecimal;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.DecimalLimit;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class LimitBoundBigDecimalGenerator extends BigDecimalGenerator implements Generator {

	@Inject
	private RandomBigDecimalGenerator rbdg;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		DecimalLimit limit = (DecimalLimit) constraintList.get(DecimalLimit.class);
		BigDecimal lowLimit = new BigDecimal(limit.lowLimit());
		BigDecimal highLimit = new BigDecimal(limit.highLimit());
		int scale = limit.scale();
		return rbdg.generate(lowLimit, highLimit,scale);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(DecimalLimit.class);
	}

}
