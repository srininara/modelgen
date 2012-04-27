package com.nacnez.util.modelgen.impl.contract;

public interface ContractDigest<T> {
	void fill(T model);
	String info();
}
