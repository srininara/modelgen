package com.nacnez.util.modelgen.impl.generator.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RandomDateGeneratorTest {

	JodaBasedRandomDateGeneratorImpl rdg;
	
	@Before
	public void buildGenerator() {
		rdg = new JodaBasedRandomDateGeneratorImpl(new JavaUtilRandomBigDecimalGeneratorImpl());
	}

	@Test
	public void generateRandomDateWithInRange() {
		int genCount = 50;
		Set<Date> generatedDates = new HashSet<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 0, 1);
		Date minDate = cal.getTime();
		cal.set(2012,5,30);
		Date maxDate = cal.getTime();
		for (int i = 0; i < genCount; i++) {
			Date d = rdg.generate(minDate,maxDate);
			assertTrue(d.compareTo(minDate)>=0);
			assertTrue(d.compareTo(maxDate)<=0);
			generatedDates.add(d);
		}
		assertEquals(genCount, generatedDates.size());
	}

	@Test
	public void generateRandomDates() {
		int genCount = 50;
		Set<Date> generatedDates = new HashSet<Date>();
		for (int i = 0; i < genCount; i++) {
			Date d = rdg.generate();
			generatedDates.add(d);
		}
		assertEquals(genCount, generatedDates.size());
		
	}
	
}
