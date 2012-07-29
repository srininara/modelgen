package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.util.List;

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
	
	@Test
	public void evenIfConstraintsAreSentBasicGeneratorWillAlwaysGenerateAValueIrrespectiveOfWhetherTheNextGeneratorIsPresent() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicStringGenerator bsg = injector.getInstance(BasicStringGenerator.class);
		Generator anotherGen = mock(Generator.class);
		@SuppressWarnings("unchecked")
		List<Annotation> constraints = mock(List.class);
		when(constraints.size()).thenReturn(1);
		String anotherValue = "anotherValue";
		when(anotherGen.generate(constraints)).thenReturn(anotherValue);
		bsg.setNext(anotherGen);
		String output = (String) bsg.generate(constraints);
		assertNotNull(output);
		assertFalse(anotherValue.equals(output));
		verify(anotherGen, never()).generate(constraints);
	}
}
