package com.nacnez.util.modelgen.factory;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.nacnez.util.modelgen.impl.generator.Generator;
import com.nacnez.util.modelgen.impl.generator.SizedStringDecorator;
import com.nacnez.util.modelgen.impl.generator.StringGenerator;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;

public class TypeToGeneratorMappingProvider implements Provider<TypeToGeneratorMapping> {
	
	@Inject @Named("Basic")
	StringGenerator bsg; 

	@Inject @Named("Alphabetic")
	StringGenerator asg; 
	
	@Inject @Named("Alphanumeric")
	StringGenerator ansg;

	@Inject @Named("StringFromList")
	StringGenerator sflg;

	public TypeToGeneratorMapping get() {
		Map<Class, Generator> typeToGeneratorMapping = new HashMap<Class, Generator>();
		typeToGeneratorMapping.put(String.class, getStringGenerator());
		return new TypeToGeneratorMapping(typeToGeneratorMapping);
	}


	private Generator getStringGenerator() {
		Generator sizedNormalStringGenerator = new SizedStringDecorator(bsg); 
		sizedNormalStringGenerator.setNext((Generator)bsg);
		
		asg.setNext(sizedNormalStringGenerator);
		
		Generator sizedAlphabeticStringGenerator = new SizedStringDecorator(asg); 
		sizedAlphabeticStringGenerator.setNext((Generator)asg);

		ansg.setNext(sizedAlphabeticStringGenerator);
		
		Generator sizedAlphanumericStringGenerator = new SizedStringDecorator(ansg); 
		sizedAlphanumericStringGenerator.setNext((Generator)ansg);
		
		sflg.setNext(sizedAlphanumericStringGenerator);
		
		return (Generator)sflg;
	}
	

}
