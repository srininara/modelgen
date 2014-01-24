package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

public class BasicDoubleGeneratorTest {

	@Test
	public void testBasicGeneration() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicDoubleGenerator bdg = injector.getInstance(BasicDoubleGenerator.class);
		Double num = (Double) bdg.generate((List<Annotation>)null);
		assertNotNull(num);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void evenIfConstraintsAreSentBasicGeneratorWillAlwaysGenerateAValueIrrespectiveOfWhetherTheNextGeneratorIsPresent() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicDoubleGenerator big = injector.getInstance(BasicDoubleGenerator.class);
		Generator anotherGen = mock(Generator.class);
		List<Annotation> constraints = mock(List.class);
		when(constraints.size()).thenReturn(1);
		Integer anotherValue = 123;
		when(anotherGen.generate(constraints)).thenReturn(anotherValue);
		big.setNext(anotherGen);
		Double output = (Double) big.generate(constraints);
		assertNotNull(output);
		assertFalse(anotherValue.equals(output));
		verify(anotherGen, never()).generate(constraints);
	}

}
