package com.nacnez.util.modelgen.impl.generator;

import java.util.Collection;

public interface DataGenerator<T> {

	DataGenerator<T> provideRandomData();
	
	DataGenerator<T> ofLength(int size);

	DataGenerator<T> provideRandomDataFrom(Collection<T> possibleValues);

	T generate();
	
}
