package com.nacnez.util.modelgen.impl.generator.model;

import java.util.Map;

import com.nacnez.util.modelgen.impl.generator.Generator;

public class TypeToGeneratorMapping {
	
	@SuppressWarnings("rawtypes")
	Map<Class,Generator> mapping;
	
	public TypeToGeneratorMapping(@SuppressWarnings("rawtypes") Map<Class,Generator> mapping) {
		this.mapping = mapping;
	}
	
	public Generator get(@SuppressWarnings("rawtypes") Class type) {
		return mapping.get(type);
	}
	

}
