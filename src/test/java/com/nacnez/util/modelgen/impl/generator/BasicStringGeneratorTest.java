package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;

public class BasicStringGeneratorTest {

	@Test
	public void testBasicGeneration() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicStringGenerator bsg = injector.getInstance(BasicStringGenerator.class);
		String str = (String) bsg.generate(null);
		assertNotNull(str);
		assertTrue(str.length() > 0);
	}
	
}
