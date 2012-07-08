package com.nacnez.util.modelgen.factory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.nacnez.util.modelgen.impl.generator.AlphabeticStringGenerator;
import com.nacnez.util.modelgen.impl.generator.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.BasicStringGenerator;
import com.nacnez.util.modelgen.impl.generator.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.RandomIntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;
import com.nacnez.util.modelgen.impl.generator.StringGenerator;

public class ModelGenModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RandomStringGenerator.class).to(ApacheCommonsRandomStringGeneratorImpl.class);
		bind(StringGenerator.class).annotatedWith(Names.named("Basic")).to(BasicStringGenerator.class);
		bind(RandomIntegerGenerator.class).to(JavaUtilRandomIntegerGeneratorImpl.class);
		bind(StringGenerator.class).annotatedWith(Names.named("Alphabetic")).to(AlphabeticStringGenerator.class);
	}

}
