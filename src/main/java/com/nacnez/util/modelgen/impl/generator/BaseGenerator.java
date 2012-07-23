package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public abstract class BaseGenerator {

	protected Generator next;

	protected ConstraintList convert(List<Annotation> constraints) {
		return new ConstraintList(constraints);
	}

	public BaseGenerator() {
		super();
	}

	public void setNext(Generator next) {
		this.next = next;
	}

	protected abstract boolean applicable(ConstraintList constraintList);

}