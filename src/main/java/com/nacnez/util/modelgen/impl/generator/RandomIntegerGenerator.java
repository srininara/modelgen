package com.nacnez.util.modelgen.impl.generator;


public interface RandomIntegerGenerator {

	Integer generate();
	
	Integer generate(int highLimit);

	Integer generate(int lowLimit, int highLimit);
}