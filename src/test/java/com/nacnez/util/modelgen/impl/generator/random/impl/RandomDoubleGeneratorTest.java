package com.nacnez.util.modelgen.impl.generator.random.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

public class RandomDoubleGeneratorTest {

	JavaUtilRandomDoubleGeneratorImpl rdg;

	Mirror mirror = new Mirror();

	@Before
	public void setup() {
		rdg = new JavaUtilRandomDoubleGeneratorImpl();
	}

	@Test
	public void generateRandomDoublesWithDefaultSettings() {
		int genCount = 50;
		Set<Double> generatedNumbers = new HashSet<Double>();
	
		for (int i = 0; i < genCount; i++) {
			Double d = rdg.generate();
			generatedNumbers.add(d);
		}
		assertEquals(genCount, generatedNumbers.size());
	}


	
	@Test
	public void generateRandomDoublesWithRangeAndScale() {
		int genCount = 50;
		Set<Double> generatedNumbers = new HashSet<Double>();
		Double minValue =  new Double(10000.25);
		Double maxValue =  new Double(1000000.75);
		
		for (int i = 0; i < genCount; i++) {
			Double d = rdg.generate(minValue,maxValue);
			assertTrue(d.compareTo(minValue)>=0);
			assertTrue(d.compareTo(maxValue)<=0);
			generatedNumbers.add(d);
		}
		assertEquals(genCount, generatedNumbers.size());
	}

}
