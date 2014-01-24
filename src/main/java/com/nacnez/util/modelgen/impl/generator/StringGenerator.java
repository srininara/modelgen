package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public abstract class StringGenerator extends BaseGenerator {
	
	public static final int DEFAULT_LENGTH = 32;

	public StringGenerator() {
		super();
	}
	
	public final Object doGenerate(List<Annotation> constraints) {
		ConstraintList list = convert(constraints);
		if (applicable(list)) {
			return doGenerate(list,getLength(list));
		} 

		if (next!=null) {
			return next.generate(constraints);
		}
		return null;
	}
	
	protected abstract Object doGenerate(ConstraintList constraintList, int size);
	
	protected int getLength(ConstraintList constraintList) {
		return DEFAULT_LENGTH;
	}
	
}