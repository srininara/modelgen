package com.nacnez.util.modelgen.impl.generator.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.joda.time.DateMidnight;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.impl.generator.RandomBigDecimalGenerator;
import com.nacnez.util.modelgen.impl.generator.RandomDateGenerator;

public class JodaBasedRandomDateGeneratorImpl implements RandomDateGenerator {

	private RandomBigDecimalGenerator rbdg;
	
	private static Random random = new Random();
	
	@Inject
	public JodaBasedRandomDateGeneratorImpl(RandomBigDecimalGenerator rbdg) {
		this.rbdg = rbdg;
	}

	public Date generate(Date minDate, Date maxDate) {
		DateMidnight internalMinDate = new DateMidnight(minDate.getTime());
		DateMidnight internalMaxDate = new DateMidnight(maxDate.getTime());
		return generate(internalMinDate,internalMaxDate);
	}
	
	public Date generate() {
		DateMidnight internalMinDate = DateMidnight.parse("2000-01-01");
		DateMidnight internalMaxDate = DateMidnight.parse("2012-01-01");
		return generate(internalMinDate,internalMaxDate);
	}

	public Date generate(DateMidnight internalMinDate, DateMidnight internalMaxDate) {
		long internalMinTime = internalMinDate.getMillis();
		long internalMaxTime = internalMaxDate.getMillis();
		long randomTime = generate(internalMinTime,internalMaxTime);
		DateMidnight randomInternalDate = new DateMidnight(randomTime);
		return randomInternalDate.toDate();
	}
	
	
	private Long generate(long lowLimit, long highLimit) {
		BigDecimal lowLBD = new BigDecimal(lowLimit);
		BigDecimal hiLBD = new BigDecimal(highLimit);
		BigDecimal randBD = rbdg.generate(lowLBD, hiLBD, 0);
		long m = randBD.longValue();
		return m;
	}

}
