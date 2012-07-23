package com.nacnez.util.modelgen.impl.generator.impl;

import java.util.Random;

import com.nacnez.util.modelgen.impl.generator.RandomIntegerGenerator;

public class JavaUtilRandomIntegerGeneratorImpl implements RandomIntegerGenerator {  

	private static Random random = new Random();
	
	public Integer generate() {
		return generate(Integer.MAX_VALUE-1);
	}

	public Integer generate(int highLimit) {
		return generate(0,highLimit);
	}

	public Integer generate(int lowLimit, int highLimit) {
		int range = Math.abs(highLimit - lowLimit);
		int m = random.nextInt( range + 1 ) + lowLimit;
		return m;
	}

}
