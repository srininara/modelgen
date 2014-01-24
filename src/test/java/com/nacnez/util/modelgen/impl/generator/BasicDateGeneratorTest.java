package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;

public class BasicDateGeneratorTest {

	@Test
	public void testBasicGeneration() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicDateGenerator bdg = injector.getInstance(BasicDateGenerator.class);
		Date num = (Date) bdg.generate((List<Annotation>)null);
		assertNotNull(num);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void evenIfConstraintsAreSentBasicGeneratorWillAlwaysGenerateAValueIrrespectiveOfWhetherTheNextGeneratorIsPresent() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicDateGenerator bdg = injector.getInstance(BasicDateGenerator.class);
		Generator anotherGen = mock(Generator.class);
		List<Annotation> constraints = mock(List.class);
		when(constraints.size()).thenReturn(1);
		Date anotherValue = new Date();
		when(anotherGen.generate(constraints)).thenReturn(anotherValue);
		bdg.setNext(anotherGen);
		Date output = (Date) bdg.generate(constraints);
		assertNotNull(output);
		assertFalse(anotherValue.equals(output));
		verify(anotherGen, never()).generate(constraints);
	}

}


