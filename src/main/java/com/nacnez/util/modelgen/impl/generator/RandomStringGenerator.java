package com.nacnez.util.modelgen.impl.generator;


public interface RandomStringGenerator extends Generator {

	int DEFAULT_LENGTH = 32;
	
	String generate();

	String generate(int length);

	String generateAlphaNumeric();

	String generateAlphaNumeric(int length);

	String generateAlphabetic();

	String generateAlphabetic(int length);

}
