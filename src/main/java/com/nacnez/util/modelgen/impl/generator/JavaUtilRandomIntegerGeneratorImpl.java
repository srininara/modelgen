package com.nacnez.util.modelgen.impl.generator;

import static com.nacnez.util.modelgen.impl.generator.GeneratorUtil.isConstraintPresent;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;

import com.nacnez.util.modelgen.impl.generator.rules.InvalidConstraintException;
import com.nacnez.util.modelgen.impl.generator.rules.Limit;
import com.nacnez.util.modelgen.impl.generator.rules.Negative;

public class JavaUtilRandomIntegerGeneratorImpl<Integer> implements RandomIntegerGenerator, Generator<Integer> {

	private static final String INVALID_LIMIT_CONSTRAINTS = "Invalid limit constraints";
	private static Random random = new Random();
	
	public java.lang.Integer generate() {
		int output = random.nextInt(); 
		int finalOutput = (output>0?output:-1*output);
		return new java.lang.Integer(finalOutput);
	}

	public java.lang.Integer generateNegative() {
		return -1 * generate();
	}

	public java.lang.Integer generate(int highLimit) {
		return random.nextInt(highLimit);
	}

	public java.lang.Integer generateNegative(int loLimit) {
		int absLimit = Math.abs(loLimit);
		return -1 * generate(absLimit);
	}

	public Integer generate(List<Annotation> constraints) {
		int index = -1;
		int loLimit = 0;
		int hiLimit = 0;
		boolean negativeReqd = false;
		boolean limitApplicable = false;
		if (constraints==null) {
			return (Integer) generate();
		}
		
		if((index=isConstraintPresent(constraints,Limit.class))!=-1) {
			limitApplicable = true;
			Limit limit = (Limit)constraints.get(index);
			hiLimit = limit.highLimit();
			loLimit = limit.lowLimit();
		}
		
		index = -1;
		if((index=isConstraintPresent(constraints,Negative.class))!=-1) {
			negativeReqd = true;
		}
		
		if (hiLimit>0 && loLimit>0) {
			throw new InvalidConstraintException(INVALID_LIMIT_CONSTRAINTS);
		}
		
		if (loLimit<0 && hiLimit<0) {
			throw new InvalidConstraintException(INVALID_LIMIT_CONSTRAINTS);
		}
		
		if (!negativeReqd && (hiLimit < 0 || loLimit < 0)) {
			throw new InvalidConstraintException(INVALID_LIMIT_CONSTRAINTS);
		}
		
		if (negativeReqd && (hiLimit > 0 || loLimit > 0)) {
			throw new InvalidConstraintException(INVALID_LIMIT_CONSTRAINTS);
		}

		if (negativeReqd && !limitApplicable) {
			return (Integer)generateNegative();
		}
		
		if (negativeReqd && limitApplicable) {
			return (Integer)generateNegative(loLimit);
		}
		
		return (Integer)generate(hiLimit);

	}
	
}
