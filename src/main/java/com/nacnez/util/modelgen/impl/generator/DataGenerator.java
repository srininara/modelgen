package com.nacnez.util.modelgen.impl.generator;

import java.util.Collection;

public interface DataGenerator<T> {

	DataGenerator<T> initToProvide();
	
	DataGenerator<T> randomData();
	
	DataGenerator<T> ofLength(int size);

	DataGenerator<T> dataFrom(Collection<T> possibleValues);

	T generate();
	
}
