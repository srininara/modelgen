package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class ApacheCommonsRandomStringGeneratorImpl implements
		RandomStringGenerator {

	public String generate() {
		return generate(DEFAULT_LENGTH);
	}

	public String generate(int length) {
		return RandomStringUtils.random(length);
	}

	public String generateAlphaNumeric() {
		return generateAlphaNumeric(DEFAULT_LENGTH);
	}

	public String generateAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateAlphabetic() {
		return generateAlphabetic(DEFAULT_LENGTH);
	}

	public String generateAlphabetic(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public String generate(List<Annotation> constraints) {
		int size = DEFAULT_LENGTH;
		int index = -1;
		boolean alpha = false;
		boolean alphaNumeric = false;
		
		if((index=isConstraintPresent(constraints,Size.class))!=-1) {
			size = ((Size)constraints.get(index)).maxSize();
		}
		if(isConstraintPresent(constraints,Alphabetic.class)!=-1) {
			alpha = true;
		}
		if (isConstraintPresent(constraints, Alphanumeric.class)!= -1) {
			alphaNumeric = true;
		}
		if (alpha) {
			return generateAlphabetic(size);
		} else if (alphaNumeric) {
			return generateAlphaNumeric(size);
		} else {
			return generate(size);
		}
	}

	private int isConstraintPresent(List<Annotation> constraints,
			Class<?> targetConstraintClass) {
		for (int i=0;i<constraints.size();i++) {
			if(constraints.get(i).annotationType().equals(targetConstraintClass)) {
				return i;
			}
		}
		return -1;
	}
	
	
}