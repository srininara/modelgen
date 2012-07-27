package com.nacnez.util.modelgen.impl.generator;

import java.util.Date;

public interface RandomDateGenerator {

	Date generate(Date minDate, Date maxDate);
}
