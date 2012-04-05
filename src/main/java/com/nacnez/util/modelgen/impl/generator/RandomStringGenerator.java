package com.nacnez.util.modelgen.impl.generator;

public interface RandomStringGenerator {

	int DEFAULT_LENGTH = 32;
	
	String generateString();

	String generateString(int length);

	String generateAlphaNumericString();

	String generateAlphaNumericString(int length);

	String generateAlphabeticString();

	String generateAlphabeticString(int length);

}
