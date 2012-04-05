package com.nacnez.util.modelgen.impl.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class ApacheCommonsRandomStringGeneratorImpl implements
		RandomStringGenerator {

	public String generateString() {
		return generateString(DEFAULT_LENGTH);
	}

	public String generateString(int length) {
		return RandomStringUtils.random(length);
	}

	public String generateAlphaNumericString() {
		return generateAlphaNumericString(DEFAULT_LENGTH);
	}

	public String generateAlphaNumericString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateAlphabeticString() {
		return generateAlphabeticString(DEFAULT_LENGTH);
	}

	public String generateAlphabeticString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
}