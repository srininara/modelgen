package com.nacnez.util.modelgen.impl.generator;

import java.util.Random;

public class JavaUtilRandomIntegerGeneratorImpl implements
		RandomIntegerGenerator {

	private static Random random = new Random();
	
	public int generate() {
		return random.nextInt();
	}

	public int generateNegative() {
		return -1 * generate();
	}

	public int generate(int highLimit) {
		return random.nextInt(highLimit);
	}

	public int generateNegative(int loLimit) {
		int absLimit = Math.abs(loLimit);
		return -1 * generate(absLimit);
	}
	
}
