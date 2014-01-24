package com.nacnez.util.modelgen.impl.generator.random;

import java.util.Date;

import org.joda.time.DateMidnight;

public interface RandomDateGenerator {

	Date generate();

	Date generate(Date minDate, Date maxDate);
	
	Date generate(DateMidnight minDate, DateMidnight maxDate);
	
}
