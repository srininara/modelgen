package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.impl.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class RandomStringGeneratorTest {

	ApacheCommonsRandomStringGeneratorImpl rsg;
	
	Mirror mirror = new Mirror();
	
	@Before
	public void setup() {
		// Makes this a bit more of classic static driven test
		// Also means that it is leaning more towards integration test. 
		// Might want to move to a behaviour driven test. Not sure.
		rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl()); 
	}
	
	@Test
	public void generateRandomStringWithNoConstraints() {
		String generated = rsg.generate(null);
		assertNotNull(generated);
	}
	
	@Test
	public void generateRandomStringWithSizeConstraint() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		int size = ((Size)constraints.get(0)).maxSize();
		String generated = rsg.generate(constraints);
		assertEquals(size, generated.length());
	}

	@Test
	public void generatorMustGenerateARandomStringWhichHasOnlyAlphabetsAndIsOfGivenSize() {
		List<Annotation> constraints = getConstraints("setFirstName");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		int size = ((Size)constraints.get(0)).maxSize();
		assertTrue(constraints.get(1).annotationType().equals(Alphabetic.class));
		String generated = rsg.generate(constraints);
		assertEquals(size,generated.length());
		assertTrue(StringUtils.isAlpha(generated));
	}
	
	@Test
	public void generatorMustGenerateARandomStringWhichIsAlphanumericAndIsOfGivenSize() {
		List<Annotation> constraints = getConstraints("setPAN");
		assertEquals(2,constraints.size());
		assertTrue(constraints.get(1).annotationType().equals(Size.class));
		int size = ((Size)constraints.get(1)).maxSize();
		assertTrue(constraints.get(0).annotationType().equals(Alphanumeric.class));
		String generated = rsg.generate(constraints);
		assertEquals(size,generated.length());
		assertTrue(StringUtils.isAlphanumeric(generated));
	}
	
	@Test
	public void generatorMustGenerateARandomStringWhichIsFromAnArrayOfPossibleValues() {
		List<Annotation> constraints = getConstraints("setMaritalStatus");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(FromList.class));
		String[] fromList = ((FromList)constraints.get(0)).fromList();
		String generated = rsg.generate(constraints);
		assertTrue(Arrays.asList(fromList).contains(generated));
	}
	
	@Test
	public void generateRandomStringOfDefaultLength() {
		String firstOutput = rsg.generate();
		assertNotNull(firstOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		String secondOutput = rsg.generate();
		assertNotNull(secondOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generate(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generate(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}

	@Test
	public void generateRandomAlphaNumericStringOfDefaultLength() {
		String firstOutput = rsg.generateAlphaNumeric();
		assertNotNull(firstOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		String secondOutput = rsg.generateAlphaNumeric();
		assertNotNull(secondOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphaNumericStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generateAlphaNumeric(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generateAlphaNumeric(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphabeticStringOfDefaultLength() {
		String firstOutput = rsg.generateAlphabetic();
		assertNotNull(firstOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		String secondOutput = rsg.generateAlphabetic();
		assertNotNull(secondOutput);
		assertEquals(ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphabeticStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generateAlphabetic(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generateAlphabetic(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}

	private List<Annotation> getConstraints(String methodName) {
		return mirror.on(PersonContract.class).reflectAll().annotations().atMethod(methodName).withArgs(String.class);
	}
	
	
}
