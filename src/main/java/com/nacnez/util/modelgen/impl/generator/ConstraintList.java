package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public class ConstraintList {

	private List<Annotation> backingList;

	public ConstraintList(List<Annotation> backingConstraintsList) {
		this.backingList = backingConstraintsList;
	}

	public boolean contains(Class<? extends Annotation> constraintClass) {
		for (Annotation constraint : backingList) {
			if (constraintSame(constraint,constraintClass)) {
				return true;
			}
		}
		return false;
	}

	public Annotation get(Class<? extends Annotation> constraintClass) {
		for (Annotation constraint : backingList) {
			if (constraintSame(constraint,constraintClass)) {
				return constraint;
			}
		}
		return null;
	}
	
	private boolean constraintSame(Annotation constraint, Class<? extends Annotation> constraintClass) {
		return (constraint.annotationType().equals(constraintClass));
	}

}