package com.nacnez.util.modelgen.impl;

import java.util.Collection;
import java.util.Map;

import com.nacnez.util.modelgen.ModelContract;
import com.nacnez.util.modelgen.ModelGenerator;

public class SimpleModelGenerator implements ModelGenerator {

	private long numberOfModelObjs;
	
	private Object prototypeModelObj;
	
	private ModelContract modelContract;

	public ModelGenerator make(long numberOfModelObjs) {
		this.numberOfModelObjs = numberOfModelObjs;
		return this;
	}

	public ModelGenerator ofThis(Object modelObj, ModelContract modelContract) {
		this.prototypeModelObj = modelObj;
		this.modelContract = modelContract;
		return this;
	}

	public ModelGenerator quickly(int numberOfThreads) {
		// Does nothing in the simple version
		return this;
	}

	public Collection<?> andProvideAs(Collection<?> c) {
		// create a holder collection
		// start a loop
		// create a empty model using the prototype
		// decipher the contract to figure out the setters
		// lookout for annotations which tell you how to generate the data
		// use the setters to set it into the empty model
		// add the model to the collection
		return null;
	}

	public void andFillUpThis(Collection<?> c) {

	}

	public void andFillUpThis(Map<?, ?> m) {

	}

}
