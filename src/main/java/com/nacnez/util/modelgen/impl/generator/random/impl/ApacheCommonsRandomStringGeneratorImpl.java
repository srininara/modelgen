package com.nacnez.util.modelgen.impl.generator.random.impl;

import org.apache.commons.lang3.RandomStringUtils;

import com.nacnez.util.modelgen.impl.generator.random.RandomStringGenerator;

// Support Ascii characters and their subsets

public class ApacheCommonsRandomStringGeneratorImpl implements
		RandomStringGenerator {
	
	public String generate(int length) {
		return RandomStringUtils.randomAscii(length);
	}

	public String generateAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateAlphabetic(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

}