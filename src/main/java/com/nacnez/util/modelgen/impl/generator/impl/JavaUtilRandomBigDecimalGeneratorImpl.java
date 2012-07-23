package com.nacnez.util.modelgen.impl.generator.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class JavaUtilRandomBigDecimalGeneratorImpl {
	
	public static final int DEFAULT_SCALE = 2;
	
	public static final BigDecimal DEFAULT_LOW_LIMT = BigDecimal.ZERO;
	
	public static final BigDecimal DEFAULT_HIGH_LIMIT = new BigDecimal("1000000");

	private static Random random = new Random();
	
	public BigDecimal generate(BigDecimal lowLimit, BigDecimal highLimit,int scale) {
		BigDecimal range = highLimit.subtract(lowLimit).abs();
		BigInteger rangeBI = range.toBigInteger();
		BigInteger factor = BigInteger.TEN.pow(scale);
		BigInteger randomGenSeed = rangeBI.multiply(factor);
		BigInteger randomNum;
		do {
			randomNum = new BigInteger(randomGenSeed.bitLength(), random);
	    } while (randomNum.compareTo(randomGenSeed) >= 0);
		BigDecimal randomInc = new BigDecimal(randomNum, scale);
		BigDecimal randomOutput = randomInc.add(lowLimit);
		return randomOutput;
	}

	public BigDecimal generate() {
		return generate(DEFAULT_LOW_LIMT,DEFAULT_HIGH_LIMIT,DEFAULT_SCALE);
	}

}
