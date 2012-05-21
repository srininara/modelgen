package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public abstract class GeneratorUtil {

	public static int isConstraintPresent(List<Annotation> constraints,
			Class<?> targetConstraintClass) {
		for (int i=0;i<constraints.size();i++) {
			if(constraints.get(i).annotationType().equals(targetConstraintClass)) {
				return i;
			}
		}
		return -1;
	}
}
