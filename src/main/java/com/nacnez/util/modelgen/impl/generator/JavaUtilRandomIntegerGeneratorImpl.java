package com.nacnez.util.modelgen.impl.generator;

import java.util.Random;

public class JavaUtilRandomIntegerGeneratorImpl implements RandomIntegerGenerator {  

	private static final String INVALID_LIMIT_CONSTRAINTS = "Invalid limit constraints";
	private static Random random = new Random();
	

	public Integer generate() {
		int output = random.nextInt(); 
		int finalOutput = (output>0?output:-1*output);
		return new java.lang.Integer(finalOutput);
	}

	public Integer generateNegative() {
		return -1 * generate();
	}

	public Integer generate(int highLimit) {
		return random.nextInt(highLimit);
	}

	public Integer generateNegative(int loLimit) {
		int absLimit = Math.abs(loLimit);
		return -1 * generate(absLimit);
	}

}
