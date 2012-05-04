package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class RandomStringGeneratorTest {

	RandomStringGenerator rsg;
	
	@Before
	public void setup() {
		rsg = new ApacheCommonsRandomStringGeneratorImpl();
	}
	
	@Test
	public void generateRandomStringWithSizeConstraint() {
		//List<Annotation> constraints = new ArrayList<Annotation>();
		List<Annotation> constraints = new Mirror().on(PersonContract.class).reflectAll().annotations().atMethod("setName").withArgs(String.class);
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		int size = ((Size)constraints.get(0)).maxSize();
		String generated = rsg.generate(constraints);
		assertEquals(size, generated.length());
	}
	
	@Test
	public void generateRandomStringOfDefaultLength() {
		String firstOutput = rsg.generate();
		assertNotNull(firstOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		String secondOutput = rsg.generate();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
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
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		String secondOutput = rsg.generateAlphaNumeric();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
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
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		String secondOutput = rsg.generateAlphabetic();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
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

	
}
