package com.nacnez.util.modelgen.impl.factory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.nacnez.util.modelgen.impl.generator.Generator;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;

public class TypeToGeneratorMappingProvider implements Provider<TypeToGeneratorMapping> {
	
	@Inject @Named("BasicString")
	Generator bsg; 

	@Inject @Named("Alphabetic")
	Generator asg; 
	
	@Inject @Named("Alphanumeric")
	Generator ansg;

	@Inject @Named("StringFromList")
	Generator sflg;
	
	@Inject @Named("BasicInteger")
	Generator big;
	
	@Inject @Named("LimitedInteger")
	Generator lbig;
	
	@Inject @Named("BasicBigDecimal")
	Generator bbdg;
	
	@Inject @Named("LimitedBigDecimal")
	Generator lbbdg;
	
	@Inject @Named("BasicDouble")
	Generator bdog;
	
	@Inject @Named("LimitedDouble")
	Generator lbdog;
	
	@Inject @Named("BasicDate")
	Generator bdg;
	
	@Inject @Named("LimitedDate")
	Generator lbdg;

	public TypeToGeneratorMapping get() {
		@SuppressWarnings("rawtypes")
		Map<Class, Generator> typeToGeneratorMapping = new HashMap<Class, Generator>();
		typeToGeneratorMapping.put(String.class, getStringGenerator());
		typeToGeneratorMapping.put(Integer.class,getIntegerGenerator());
		typeToGeneratorMapping.put(BigDecimal.class,getBigDecimalGenerator());
		typeToGeneratorMapping.put(Double.class,getDoubleGenerator());
		typeToGeneratorMapping.put(Date.class,getDateGenerator());
		return new TypeToGeneratorMapping(typeToGeneratorMapping);
	}


	private Generator getDateGenerator() {
		lbdg.setNext((Generator)bdg);
		return (Generator)lbdg;
	}


	private Generator getBigDecimalGenerator() {
		lbbdg.setNext((Generator)bbdg);
		return (Generator)lbbdg;
	}

	private Generator getDoubleGenerator() {
		lbdog.setNext((Generator)bdog);
		return (Generator)lbdog;
	}

	private Generator getIntegerGenerator() {
		lbig.setNext((Generator)big);
		return (Generator)lbig;
	}


	private Generator getStringGenerator() {
//		Generator sizedNormalStringGenerator = new SizedStringDecorator(bsg); 
//		sizedNormalStringGenerator.setNext((Generator)bsg);
		
		asg.setNext(bsg);//sizedNormalStringGenerator);
		
//		Generator sizedAlphabeticStringGenerator = new SizedStringDecorator(asg); 
//		sizedAlphabeticStringGenerator.setNext((Generator)asg);

		ansg.setNext(asg);//sizedAlphabeticStringGenerator);
		
//		Generator sizedAlphanumericStringGenerator = new SizedStringDecorator(ansg); 
//		sizedAlphanumericStringGenerator.setNext((Generator)ansg);
		
		sflg.setNext(ansg);//sizedAlphanumericStringGenerator);
		
		return (Generator)sflg;
	}
	

}
