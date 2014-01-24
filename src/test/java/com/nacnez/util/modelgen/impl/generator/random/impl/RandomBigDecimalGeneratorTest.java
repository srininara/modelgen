package com.nacnez.util.modelgen.impl.generator.random.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.impl.generator.random.impl.JavaUtilRandomBigDecimalGeneratorImpl;

public class RandomBigDecimalGeneratorTest {

	JavaUtilRandomBigDecimalGeneratorImpl rdg;

	Mirror mirror = new Mirror();

	@Before
	public void setup() {
		rdg = new JavaUtilRandomBigDecimalGeneratorImpl();
	}

	@Test
	public void generateRandomBigDecimalsWithDefaultSettings() {
		int genCount = 50;
		Set<BigDecimal> generatedNumbers = new HashSet<BigDecimal>();
	
		for (int i = 0; i < genCount; i++) {
			BigDecimal d = rdg.generate();
			generatedNumbers.add(d);
		}
		assertEquals(genCount, generatedNumbers.size());
	}


	
	@Test
	public void generateRandomBigDecimalsWithRangeAndScale() {
		int genCount = 50;
		Set<BigDecimal> generatedNumbers = new HashSet<BigDecimal>();
		BigDecimal minValue =  new BigDecimal("10000.25");
		BigDecimal maxValue =  new BigDecimal("1000000.75");
		int scale = 2;
		
		for (int i = 0; i < genCount; i++) {
			BigDecimal d = rdg.generate(minValue,maxValue,scale);
			assertTrue(d.compareTo(minValue)>=0);
			assertTrue(d.compareTo(maxValue)<=0);
			assertTrue(d.scale()==scale);
			generatedNumbers.add(d);
		}
		assertEquals(genCount, generatedNumbers.size());
	}

}
