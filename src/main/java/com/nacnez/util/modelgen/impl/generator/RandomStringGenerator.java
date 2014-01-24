package com.nacnez.util.modelgen.impl.generator;


public interface RandomStringGenerator {

	String generate();

	String generate(int length);

	String generateAlphaNumeric();

	String generateAlphaNumeric(int length);

	String generateAlphabetic();

	String generateAlphabetic(int length);

}
