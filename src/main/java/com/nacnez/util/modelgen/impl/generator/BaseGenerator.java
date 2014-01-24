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

	public final Object generate(List<Annotation> constraints) {
		ConstraintList list = convert(constraints);
		if (applicable(list)) {
			return doGenerate(list);
		}

		if (next != null) {
			return next.generate(constraints);
		}
		return null;
	}

	protected abstract Object doGenerate(ConstraintList constraintList);
}