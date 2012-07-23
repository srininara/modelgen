package com.nacnez.util.modelgen.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.ModelGenerator;
import com.nacnez.util.modelgen.impl.contract.ContractDigest;

public class SimpleModelGenerator<T> implements ModelGenerator<T> {

	private long numberOfModelObjs;

	private Class<T> prototypeModelType;

	private Class<? extends GenerationContract> contract;
	
	@Inject
	private ContractDigest digest;

	public ModelGenerator<T> make(long numberOfModelObjs) {
		this.numberOfModelObjs = numberOfModelObjs;
		return this;
	}

	public ModelGenerator<T> instancesOf(Class<T> c) {
		if (contract!=null) throw new IllegalStateException("Contract already provided hence this setup is not possible");
		this.prototypeModelType = c;
		return this;
	}

	public ModelGenerator<T> instancesWith(Class<? extends GenerationContract> contract) {
		if (prototypeModelType!=null) throw new IllegalStateException("Prototype Model type already provided hence this setup is not possible");
		this.contract = contract;
		return this;
	}

	public ModelGenerator<T> quickly(int numberOfThreads) {
		// Does nothing in the simple version
		return this;
	}

	public Collection<T> andProvideAsCollection() {
		Collection<T> outputCollection = new HashSet<T>();
		for (int i=0;i<this.numberOfModelObjs;i++) {
			T model = null;
			try {
				if (prototypeModelType!=null) {
					model = this.prototypeModelType.newInstance();
				}
				if (contract!=null) {
					model = (T) digest.digest(contract).make();
				}
				outputCollection.add(model);
			} catch (Exception e) {
				throw new RuntimeException("Problem occured", e);
			}
		}

		// create a holder collection
		// start a loop
		// create a empty model using the prototype
		// decipher the contract to figure out the setters
		// lookout for annotations which tell you how to generate the data
		// use the setters to set it into the empty model
		// add the model to the collection
		return outputCollection;
	}
	
	public void andFillUpThis(Collection<T> c) {

	}

	public void andFillUpThis(Map<? extends Object, T> m) {

	}

}
