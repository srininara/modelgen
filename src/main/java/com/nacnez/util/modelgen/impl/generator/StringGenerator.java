package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public abstract class StringGenerator {

	public StringGenerator() {
		super();
	}

	public final Object generate(List<Annotation> constraints) {
		
		if (applicable(constraints)) {
			return doGenerate(constraints);
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
	
	protected abstract Object doGenerate(List<Annotation> constraints);
	
	protected abstract boolean applicable(List<Annotation> constraints);
	


}