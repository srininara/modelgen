package com.nacnez.util.modelgen.impl.generator;

public interface RandomIntegerGenerator {

	Integer generate();

	Integer generateNegative();
	
	Integer generate(int highLimit);

	Integer generateNegative(int loLimit);
}