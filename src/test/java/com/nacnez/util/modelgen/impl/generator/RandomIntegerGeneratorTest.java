package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RandomIntegerGeneratorTest {

	RandomIntegerGenerator rig;
	
	@Before
	public void setup() {
		rig = new JavaUtilRandomIntegerGeneratorImpl();
	}
	
	@Test
	public void generateSomeRandomIntegers() {
		int genCount = 50;
		Set<Integer> generatedNumbers = new HashSet<Integer>();
		
		for (int i=0;i<genCount;i++) {
			generatedNumbers.add(rig.generate());
		}
		assertEquals(genCount,generatedNumbers.size());
	}
	
	@Test
	public void generateSomeNegativeRandomIntegers() {
		int genCount = 50;
		Set<Integer> generatedNumbers = new HashSet<Integer>();
		
		for (int i=0;i<genCount;i++) {
			generatedNumbers.add(rig.generateNegative());
		}
		assertEquals(genCount,generatedNumbers.size());
	}

	@Test
	public void generateSomeRandomIntegersWithinMaxLimit() {
		int genCount = 50;
		int highLimit = 1000000;
		Set<Integer> generatedNumbers = new HashSet<Integer>();
		
		for (int i=0;i<genCount;i++) {
			int genOutput = rig.generate(highLimit);
			assertTrue(genOutput<highLimit);
			generatedNumbers.add(genOutput);
		}
		
		assertEquals(genCount,generatedNumbers.size());
	}
	
	@Test
	public void generateSomeNegativeRandomIntegersWithinMaxLimit() {
		int genCount = 50;
		int loLimit = -1000000;
		Set<Integer> generatedNumbers = new HashSet<Integer>();
		
		for (int i=0;i<genCount;i++) {
			int genOutput = rig.generateNegative(loLimit);
			assertTrue(genOutput>loLimit);
			generatedNumbers.add(genOutput);
		}
		assertEquals(genCount,generatedNumbers.size());
	}

	
}
