package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

public interface Generator {

	String generate(List<Annotation> constraints);

}