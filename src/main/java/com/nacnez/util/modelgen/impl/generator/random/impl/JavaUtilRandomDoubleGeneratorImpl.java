package com.nacnez.util.modelgen.impl.generator.random.impl;

import java.util.Random;

import com.nacnez.util.modelgen.impl.generator.random.RandomDoubleGenerator;

public class JavaUtilRandomDoubleGeneratorImpl implements RandomDoubleGenerator {
	
	
	public static final Double DEFAULT_LOW_LIMT = 0.0;
	
	public static final Double DEFAULT_HIGH_LIMIT = new Double(1000000.0);

	private static Random random = new Random();
	
	public Double generate(Double lowLimit, Double highLimit) {
		Double range = highLimit - lowLimit;
		return lowLimit + range * random.nextDouble();
	}

	public Double generate() {
		return generate(DEFAULT_LOW_LIMT,DEFAULT_HIGH_LIMIT);
	}

}
