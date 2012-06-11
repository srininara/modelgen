package com.nacnez.util.modelgen.impl.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class ApacheCommonsRandomStringGeneratorImpl implements RandomStringGenerator {
	public static int DEFAULT_LENGTH = 32;

	private RandomIntegerGenerator rig;
	
	public ApacheCommonsRandomStringGeneratorImpl(
			RandomIntegerGenerator rig) {
		this.rig = rig;
	}

	public String generate() {
		return generate(DEFAULT_LENGTH);
	}

	public String generate(int length) {
		return RandomStringUtils.random(length);
	}

	public String generateAlphaNumeric() {
		return generateAlphaNumeric(DEFAULT_LENGTH);
	}

	public String generateAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateAlphabetic() {
		return generateAlphabetic(DEFAULT_LENGTH);
	}

	public String generateAlphabetic(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

}