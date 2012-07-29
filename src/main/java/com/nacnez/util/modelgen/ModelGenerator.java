package com.nacnez.util.modelgen;

import java.util.Collection;

public interface ModelGenerator<T> {
	ModelGenerator<T> make(long numberOfModelObjs);
	ModelGenerator<T> instancesOf(Class<T> c);
	ModelGenerator<T> instancesWith(Class<? extends GenerationContract> contract);
	ModelGenerator<T> quickly (int numberOfThreads);
	Collection<T> andProvideAsCollection();
	void andFillUpThis(Collection<T> c);
}
