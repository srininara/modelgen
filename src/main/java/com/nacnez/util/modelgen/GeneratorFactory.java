package com.nacnez.util.modelgen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.impl.SimpleModelGenerator;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;

public abstract class GeneratorFactory {
	public static <T> ModelGenerator<T> get() {
		Injector injector = Guice.createInjector(new ModelGenModule());

		ModelGenerator<T> smg = new SimpleModelGenerator<T>();
		injector.injectMembers(smg);

		return smg;
	}
	
}
