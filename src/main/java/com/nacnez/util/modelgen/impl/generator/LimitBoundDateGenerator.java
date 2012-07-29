package com.nacnez.util.modelgen.impl.generator;

import org.joda.time.DateMidnight;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.DateLimit;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class LimitBoundDateGenerator extends DateGenerator implements Generator {
	
	@Inject
	RandomDateGenerator rdg;

	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		DateLimit limit = (DateLimit) constraintList.get(DateLimit.class);
		String format = limit.format();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		DateMidnight lowLimit = formatter.parseDateTime(limit.lowLimit()).toDateMidnight();
		
		DateMidnight highLimit = formatter.parseDateTime(limit.highLimit()).toDateMidnight();
		
		return rdg.generate(lowLimit, highLimit);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(DateLimit.class);
	}
	

}
