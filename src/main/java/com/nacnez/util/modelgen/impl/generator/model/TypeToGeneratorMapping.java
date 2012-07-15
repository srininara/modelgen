package com.nacnez.util.modelgen.impl.generator.model;

import java.util.Map;

import com.nacnez.util.modelgen.impl.generator.Generator;

public class TypeToGeneratorMapping {
	
	Map<Class,Generator> mapping;
	
	public TypeToGeneratorMapping(Map<Class,Generator> mapping) {
		this.mapping = mapping;
	}
	
	public Generator get(Class type) {
		return mapping.get(type);
	}
	

}
