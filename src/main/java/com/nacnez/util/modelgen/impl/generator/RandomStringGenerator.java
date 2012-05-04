package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public interface RandomStringGenerator {

	int DEFAULT_LENGTH = 32;
	
	String generate();

	String generate(int length);

	String generateAlphaNumeric();

	String generateAlphaNumeric(int length);

	String generateAlphabetic();

	String generateAlphabetic(int length);

	String generate(List<Annotation> constraints);

}
