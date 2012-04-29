package com.nacnez.util.modelgen.impl.generator;

import java.util.Collection;

public class MethodDataGenerator<T> implements DataGenerator<T> {

	private Collection<T> possibleValues;
	
	private int size;
	
	private boolean random;
	
	private boolean fromList;
	
	public DataGenerator<T> provideRandomData() {
		return this;
	}

	public DataGenerator<T> ofLength(int size) {
		return this;
	}

	public DataGenerator<T> provideRandomDataFrom(Collection<T> possibleValues) {
		return this;
	}

	public T generate() {
		return null;
	}
	
	


}
