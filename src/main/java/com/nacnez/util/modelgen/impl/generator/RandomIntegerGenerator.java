package com.nacnez.util.modelgen.impl.generator;

public interface RandomIntegerGenerator {

	int generate();

	int generateNegative();
	
	int generate(int highLimit);

	int generateNegative(int loLimit);
}


/*
String generateString();

String generateString(int length);

String generateAlphaNumericString();

String generateAlphaNumericString(int length);

String generateAlphabeticString();

String generateAlphabeticString(int length);
*/