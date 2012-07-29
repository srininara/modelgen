package com.nacnez.util.modelgen.impl.generator.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.impl.generator.StringGenerator;

public class RandomStringGeneratorTest {

	ApacheCommonsRandomStringGeneratorImpl rsg;
	
	Mirror mirror = new Mirror();
	
	@Before
	public void setup() {
		// Makes this a bit more of classic static driven test
		// Also means that it is leaning more towards integration test. 
		// Might want to move to a behaviour driven test. Not sure.
		rsg = new ApacheCommonsRandomStringGeneratorImpl(); //new JavaUtilRandomIntegerGeneratorImpl() 
	}
	
	
	@Test
	public void generateRandomStringOfDefaultLength() {
		String firstOutput = rsg.generate();
		assertNotNull(firstOutput);
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
		String secondOutput = rsg.generate();
		assertNotNull(secondOutput);
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
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
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		String secondOutput = rsg.generateAlphaNumeric();
		assertNotNull(secondOutput);
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
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
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		String secondOutput = rsg.generateAlphabetic();
		assertNotNull(secondOutput);
		assertEquals(StringGenerator.DEFAULT_LENGTH,firstOutput.length());
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
