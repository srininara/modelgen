package com.nacnez.util.modelgen;

import java.util.Collection;
import java.util.Map;

public interface ModelGenerator {
	ModelGenerator make(long numberOfModelObjs);
	ModelGenerator ofThis(Object modelObj,ModelContract modelContract);
	ModelGenerator quickly (int numberOfThreads);
	Collection<?> andProvideAs(Collection<?> c);
	void andFillUpThis(Collection<?> c);
	void andFillUpThis(Map<?,?> m);
}
