package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.impl.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public abstract class StringGenerator {

	public StringGenerator() {
		super();
	}
	
	protected ConstraintList convert(List<Annotation> constraints) {
		return new ConstraintList(constraints);
	}


	public final Object generate(List<Annotation> constraints) {
		ConstraintList list = convert(constraints);
		if (applicable(list)) {
			return doGenerate(list,getLength(list));
		} 

		if (next!=null) {
			return next.generate(constraints);
		}
		return null;
	}
	
	protected Generator next;
	
	public void setNext(Generator next) {
		this.next = next;
	}
	
	protected abstract Object doGenerate(ConstraintList constraintList, int size);
	
	protected int getLength(ConstraintList constraintList) {
		return ApacheCommonsRandomStringGeneratorImpl.DEFAULT_LENGTH;
	}
	
	protected abstract boolean applicable(ConstraintList constraintList);
	
	
	


}