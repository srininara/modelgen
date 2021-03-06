package com.nacnez.util.modelgen.impl.generator.random.impl;

import java.util.Random;

import com.nacnez.util.modelgen.impl.generator.random.RandomIntegerGenerator;

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
		int r = random.nextInt( range + 1 );
		int m =  r + lowLimit;
		return m;
	}

}
