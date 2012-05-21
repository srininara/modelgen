package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.exampleModels.WrongContract;
import com.nacnez.util.modelgen.impl.generator.rules.InvalidConstraintException;
import com.nacnez.util.modelgen.impl.generator.rules.Limit;
import com.nacnez.util.modelgen.impl.generator.rules.Negative;

public class RandomIntegerGeneratorTest {

	JavaUtilRandomIntegerGeneratorImpl<Integer> rig;

	Mirror mirror = new Mirror();

	@Before
	public void setup() {
		rig = new JavaUtilRandomIntegerGeneratorImpl<Integer>();
	}
	
	@Test
	public void generateIntegerWithNoConstraints() {
		Integer output = (Integer) rig.generate((List<Annotation>)null);
		assertNotNull(output);
		assertTrue(output>0);
	}
	
	@Test
	public void generateIntegerWithHiLimitConstraint() {
		List<Annotation> constraints = getConstraints(PersonContract.class,"setId");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(0);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertEquals(0,loLimit); //Just ensuring that the low limit is 0 - basically does not come into play.
		
		Integer generated = (Integer)rig.generate(constraints);
		assertTrue(generated<hiLimit);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationBetweenPositiveLowAndHiLimit() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setId");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(0);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0<loLimit); //Just ensuring that the low limit is greater 0 - ensuring a exception condition
		
		rig.generate(constraints);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationOfNegativeLowLimitWithoutNegativeConstraint() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setAnotherId");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(0);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0>loLimit);
		assertTrue(0<=hiLimit);

		rig.generate(constraints);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationOfNegativeHighLimitWithoutNegativeConstraint() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setYetAnotherId");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(0);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0>hiLimit);
		assertTrue(0<=loLimit);
		
		rig.generate(constraints);
	}

	@Test
	public void generateIntegerWithNegativeConstraint() {
		List<Annotation> constraints = getConstraints(PersonContract.class,"setCreditAmount");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Negative.class));
		
		Integer generated = (Integer) rig.generate(constraints);
		assertTrue(generated.intValue()<0);
	}
	
	@Test
	public void generateIntegerWithNegativeAndLowLimitConstraint() {
		List<Annotation> constraints = getConstraints(PersonContract.class,"setLoanAmount");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Negative.class));
		assertTrue(constraints.get(1).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(1);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertEquals(0,hiLimit); //Just ensuring that the low limit is 0 - basically does not come into play.

		Integer generated = (Integer) rig.generate(constraints);
		assertTrue(generated.intValue()<0);
		assertTrue(generated.intValue()>loLimit);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationBetweenNegativeLowAndHiLimit() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setAnotherLoanAmount");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Negative.class));
		assertTrue(constraints.get(1).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(1);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0>hiLimit); //Just ensuring that the low limit is greater 0 - ensuring a exception condition
		
		rig.generate(constraints);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationOfPositiveLowLimitWithNegativeConstraint() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setAnotherAmount");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Negative.class));
		assertTrue(constraints.get(1).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(1);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0<loLimit);
		assertTrue(0>=hiLimit);

		rig.generate(constraints);
	}
	
	@Test(expected=InvalidConstraintException.class)
	public void generatorDoesNotSupportGenerationOfPositiveHighLimitWithNegativeConstraint() {
		List<Annotation> constraints = getConstraints(WrongContract.class,"setYetAnotherAmount");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Negative.class));
		assertTrue(constraints.get(1).annotationType().equals(Limit.class));
		
		Limit limit = (Limit)constraints.get(1);
		int hiLimit = limit.highLimit();
		int loLimit = limit.lowLimit();
		assertTrue(0<hiLimit);
		assertTrue(0>=loLimit);
		
		rig.generate(constraints);
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

	private List<Annotation> getConstraints(Class contract, String methodName) {
		return mirror.on(contract).reflectAll().annotations().atMethod(methodName).withArgs(Integer.class);
	}

}
