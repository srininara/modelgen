package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public interface Generator {
	int DEFAULT_LENGTH = 32;


	Object generate(List<Annotation> constraints);

}