package com.nacnez.util.modelgen;

import java.util.Collection;
import java.util.Map;

public interface ModelGenerator {
	ModelGenerator make(long numberOfModelObjs);
	ModelGenerator instancesOf(Object modelObj);
	ModelGenerator with(GenerationContract contract);
	ModelGenerator quickly (int numberOfThreads);
	Collection<?> andProvideAs(Collection<?> c);
	void andFillUpThis(Collection<?> c);
	void andFillUpThis(Map<?,?> m);
}
