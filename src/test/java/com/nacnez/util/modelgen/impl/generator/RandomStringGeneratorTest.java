package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class RandomStringGeneratorTest {

	RandomStringGenerator rsg;
	
	@Before
	public void setup() {
		rsg = new ApacheCommonsRandomStringGeneratorImpl();
	}
	
	@Test
	public void generateRandomStringOfDefaultLength() {
		String firstOutput = rsg.generateString();
		assertNotNull(firstOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		String secondOutput = rsg.generateString();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generateString(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generateString(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}

	@Test
	public void generateRandomAlphaNumericStringOfDefaultLength() {
		String firstOutput = rsg.generateAlphaNumericString();
		assertNotNull(firstOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		String secondOutput = rsg.generateAlphaNumericString();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlphanumeric(firstOutput));
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphaNumericStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generateAlphaNumericString(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generateAlphaNumericString(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphabeticStringOfDefaultLength() {
		String firstOutput = rsg.generateAlphabeticString();
		assertNotNull(firstOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		String secondOutput = rsg.generateAlphabeticString();
		assertNotNull(secondOutput);
		assertEquals(RandomStringGenerator.DEFAULT_LENGTH,firstOutput.length());
		assertTrue(StringUtils.isAlpha(firstOutput));
		assertFalse(firstOutput.equals(secondOutput));
	}
	
	@Test
	public void generateRandomAlphabeticStringOfGivenLength() {
		int length = 60;
		String firstOutput = rsg.generateAlphabeticString(length);
		assertNotNull(firstOutput);
		assertEquals(length,firstOutput.length());
		String secondOutput = rsg.generateAlphabeticString(length);
		assertNotNull(secondOutput);
		assertEquals(length,firstOutput.length());
		assertFalse(firstOutput.equals(secondOutput));
	}

	
}
