package com.nacnez.util.modelgen.impl.generator.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

public class RandomIntegerGeneratorTest {

	JavaUtilRandomIntegerGeneratorImpl rig;

	Mirror mirror = new Mirror();

	@Before
	public void setup() {
		rig = new JavaUtilRandomIntegerGeneratorImpl();
	}


	@Test
	public void generateSomeRandomIntegers() {
		int genCount = 50;
		Set<Integer> generatedNumbers = new HashSet<Integer>();

		for (int i = 0; i < genCount; i++) {
			generatedNumbers.add(rig.generate());
		}
		assertEquals(genCount, generatedNumbers.size());
	}

	@Test
	public void generateSomeRandomIntegersWithinLimits() {
		int highLimit = 100;
		int lowLimit = 0;
		int output = rig.generate(lowLimit, highLimit);
		assertTrue(output>=lowLimit && output<=highLimit);
		
		highLimit = 0;
		lowLimit = -100;
		output = rig.generate(lowLimit, highLimit);
		assertTrue(output>=lowLimit && output<=highLimit);

		highLimit = 100;
		lowLimit = -100;
		output = rig.generate(lowLimit, highLimit);
		assertTrue(output>=lowLimit && output<=highLimit);
		
		highLimit = 100;
		lowLimit = 70;
		output = rig.generate(lowLimit, highLimit);
		assertTrue(output>=lowLimit && output<=highLimit);
		
		highLimit = -1;
		lowLimit = -100;
		output = rig.generate(lowLimit, highLimit);
		assertTrue(output>=lowLimit && output<=highLimit);

	}


	@Test
	public void generateSomeRandomIntegersWithinMaxLimit() {
		int genCount = 50;
		int highLimit = 1000000;
		Set<Integer> generatedNumbers = new HashSet<Integer>();

		for (int i = 0; i < genCount; i++) {
			int genOutput = rig.generate(highLimit);
			assertTrue(genOutput < highLimit);
			generatedNumbers.add(genOutput);
		}

		assertEquals(genCount, generatedNumbers.size());
	}
	
	


	private List<Annotation> getConstraints(Class contract, String methodName) {
		return mirror.on(contract).reflectAll().annotations()
				.atMethod(methodName).withArgs(Integer.class);
	}

}
