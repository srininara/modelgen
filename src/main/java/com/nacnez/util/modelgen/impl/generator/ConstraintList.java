package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public class ConstraintList {

	private List<Annotation> backingList;

	public boolean contains(Class<? extends Annotation> constraintClass) {
		for (int i=0;i<backingList.size();i++) {
			if(backingList.get(i).annotationType().equals(constraintClass)) {
				return true;
			}
		}
		return false;
	}

	public Annotation get(Class<? extends Annotation> constraintClass) {
		for (int i=0;i<backingList.size();i++) {
			if(backingList.get(i).annotationType().equals(constraintClass)) {
				return backingList.get(i);
			}
		}
		return null;
	}

	List<Annotation> getInternalList() {
		return this.backingList;
	}
	
	public ConstraintList(List<Annotation> backingConstraintsList) {
		this.backingList = backingConstraintsList;
	}

}
