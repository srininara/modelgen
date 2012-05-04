package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class ApacheCommonsRandomStringGeneratorImpl implements
		RandomStringGenerator {

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

	public String generate(List<Annotation> constraints) {
		int size = DEFAULT_LENGTH;
		if(constraints.get(0).annotationType().equals(Size.class)) {
			size = ((Size)constraints.get(0)).maxSize();
		}
		return generate(size);
	}
}