package com.nacnez.util.modelgen.impl.factory;

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
import com.nacnez.util.modelgen.impl.generator.BasicBigDecimalGenerator;
import com.nacnez.util.modelgen.impl.generator.BasicDateGenerator;
import com.nacnez.util.modelgen.impl.generator.BasicDoubleGenerator;
import com.nacnez.util.modelgen.impl.generator.BasicIntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.BasicStringGenerator;
import com.nacnez.util.modelgen.impl.generator.BigDecimalGenerator;
import com.nacnez.util.modelgen.impl.generator.DateGenerator;
import com.nacnez.util.modelgen.impl.generator.DoubleGenerator;
import com.nacnez.util.modelgen.impl.generator.IntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.LimitBoundBigDecimalGenerator;
import com.nacnez.util.modelgen.impl.generator.LimitBoundDateGenerator;
import com.nacnez.util.modelgen.impl.generator.LimitBoundDoubleGenerator;
import com.nacnez.util.modelgen.impl.generator.LimitBoundIntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.StringFromListGenerator;
import com.nacnez.util.modelgen.impl.generator.StringGenerator;
import com.nacnez.util.modelgen.impl.generator.model.TypeToGeneratorMapping;
import com.nacnez.util.modelgen.impl.generator.random.RandomBigDecimalGenerator;
import com.nacnez.util.modelgen.impl.generator.random.RandomDateGenerator;
import com.nacnez.util.modelgen.impl.generator.random.RandomDoubleGenerator;
import com.nacnez.util.modelgen.impl.generator.random.RandomIntegerGenerator;
import com.nacnez.util.modelgen.impl.generator.random.RandomStringGenerator;
import com.nacnez.util.modelgen.impl.generator.random.impl.ApacheCommonsRandomStringGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.random.impl.JavaUtilRandomBigDecimalGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.random.impl.JavaUtilRandomDoubleGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.random.impl.JavaUtilRandomIntegerGeneratorImpl;
import com.nacnez.util.modelgen.impl.generator.random.impl.JodaBasedRandomDateGeneratorImpl;

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
		
		bind(IntegerGenerator.class).annotatedWith(Names.named("Basic")).to(BasicIntegerGenerator.class);
		bind(IntegerGenerator.class).annotatedWith(Names.named("Limited")).to(LimitBoundIntegerGenerator.class);

		bind(TypeToGeneratorMapping.class).annotatedWith(Names.named("typeGenMap")).toProvider(TypeToGeneratorMappingProvider.class);
		bind(Mirror.class).in(Singleton.class);
		bind(ContractDigest.class).to(ContractDigestImpl.class);
		bind(ModelGenerator.class).annotatedWith(Names.named("Simple")).to(SimpleModelGenerator.class);

		bind(RandomBigDecimalGenerator.class).to(
				JavaUtilRandomBigDecimalGeneratorImpl.class);
		bind(BigDecimalGenerator.class).annotatedWith(Names.named("Basic")).to(BasicBigDecimalGenerator.class);
		bind(BigDecimalGenerator.class).annotatedWith(Names.named("Limited")).to(LimitBoundBigDecimalGenerator.class);
		
		bind(RandomDoubleGenerator.class).to(
				JavaUtilRandomDoubleGeneratorImpl.class);
		bind(DoubleGenerator.class).annotatedWith(Names.named("Basic")).to(BasicDoubleGenerator.class);
		bind(DoubleGenerator.class).annotatedWith(Names.named("Limited")).to(LimitBoundDoubleGenerator.class);

		
		
		bind(RandomDateGenerator.class).to(
				JodaBasedRandomDateGeneratorImpl.class);
		bind(DateGenerator.class).annotatedWith(Names.named("Basic")).to(BasicDateGenerator.class);
		bind(DateGenerator.class).annotatedWith(Names.named("Limited")).to(LimitBoundDateGenerator.class);
		
		
	}

}
