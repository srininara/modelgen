package com.nacnez.util.modelgen.impl.generator.impl;

import static com.nacnez.util.modelgen.impl.generator.StringGenerator.DEFAULT_LENGTH;

import org.apache.commons.lang3.RandomStringUtils;

import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;

// Support Ascii characters and their subsets

public class ApacheCommonsRandomStringGeneratorImpl implements
		RandomStringGenerator {

	public String generate() {
		return generate(DEFAULT_LENGTH);
	}

	public String generate(int length) {
		return RandomStringUtils.randomAscii(length);
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