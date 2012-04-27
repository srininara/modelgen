package com.nacnez.util.modelgen.impl.contract;

import java.util.Collection;
import java.util.HashSet;

public class CompositeContractDigest<T> implements ContractDigest<T> {

	Collection<ContractDigest<T>> digests = new HashSet<ContractDigest<T>>();
	
	public void fill(T model) {
		
	}

	public String info() {
		StringBuilder infoBuffer = new StringBuilder();
		for (ContractDigest<T> digest: digests) {
			infoBuffer.append(digest.info());
			infoBuffer.append(",");
		}
		return infoBuffer.substring(0, infoBuffer.length()-2);
	}
	
	public void set(Collection<ContractDigest<T>> digests) {
		this.digests = digests;
	}
	
	public void add(ContractDigest<T> digest) {
		this.digests.add(digest);
	}

}
