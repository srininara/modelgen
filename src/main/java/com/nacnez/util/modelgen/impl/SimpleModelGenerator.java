package com.nacnez.util.modelgen.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.ModelGenerator;
import com.nacnez.util.modelgen.impl.contract.ContractDigest;
import com.nacnez.util.modelgen.impl.contract.ContractDigestImpl;

public class SimpleModelGenerator<T> implements ModelGenerator<T> {

	private long numberOfModelObjs;

	private Class<T> prototypeModelType;

	private Class<? extends GenerationContract> contract;

	public ModelGenerator<T> make(long numberOfModelObjs) {
		this.numberOfModelObjs = numberOfModelObjs;
		return this;
	}

	public ModelGenerator<T> instancesOf(Class<T> c) {
		this.prototypeModelType = c;
		return this;
	}

	public ModelGenerator<T> with(Class<? extends GenerationContract> contract) {
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
			try {
				T model = this.prototypeModelType.newInstance();
				getDigest().digest(contract).fill(model);
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
	
	ContractDigest getDigest() {
		ContractDigest cd = new ContractDigestImpl();
		return cd;
	}

	public void andFillUpThis(Collection<T> c) {

	}

	public void andFillUpThis(Map<? extends Object, T> m) {

	}

}
