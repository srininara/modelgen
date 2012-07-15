package com.nacnez.util.modelgen.factory;

import net.vidageek.mirror.dsl.Mirror;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.nacnez.util.modelgen.ModelGenerator;
import com.nacnez.util.modelgen.impl.SimpleModelGenerator;
import com.nacnez.util.modelgen.impl.contract.ContractDigest;
import com.nacnez.util.modelgen.impl.contract.ContractDigestImpl;
import com.nacnez.util.modelgen.impl.generator.AlphabeticStringGenerator;
import com.nacnez.util.modelgen.impl.generator.AlphanumericStringGenerator;
import com.nacnez.util.modelgen.impl.generator.BasicStringGenerator;
import com.nacnez.util.modelgen.impl.generator.RandomIntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.RandomStringGenerator;
import com.nacnez.util.modelgen.impl.generator.StringFromListGenerator;
import com.nacnez.util.modelgen.impl.generator.StringGenerator;
import com.nacnez.util.modelgen.impl.generator.impl.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.impl.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;

public class ModelGenModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RandomStringGenerator.class).to(
				ApacheCommonsRandomStringGeneratorImpl.class);
		bind(StringGenerator.class).annotatedWith(Names.named("Basic")).to(
				BasicStringGenerator.class);
		bind(RandomIntegerGenerator.class).to(
				JavaUtilRandomIntegerGeneratorImpl.class);
		bind(StringGenerator.class).annotatedWith(Names.named("Alphabetic"))
				.to(AlphabeticStringGenerator.class);
		bind(StringGenerator.class).annotatedWith(Names.named("Alphanumeric"))
		.to(AlphanumericStringGenerator.class);
		bind(StringGenerator.class).annotatedWith(Names.named("StringFromList"))
		.to(StringFromListGenerator.class);

		bind(TypeToGeneratorMapping.class).annotatedWith(Names.named("typeGenMap")).toProvider(TypeToGeneratorMappingProvider.class);
		bind(Mirror.class).in(Singleton.class);
		bind(ContractDigest.class).to(ContractDigestImpl.class);
		bind(ModelGenerator.class).annotatedWith(Names.named("Simple")).to(SimpleModelGenerator.class);
	}

}
