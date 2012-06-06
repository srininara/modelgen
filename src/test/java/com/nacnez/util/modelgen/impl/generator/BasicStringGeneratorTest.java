package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.lang.annotation.Annotation;
import java.util.List;

import org.junit.Test;


public class BasicStringGeneratorTest {

	@Test
	public void testBasicGeneration() {
		BasicStringGenerator bsg = new BasicStringGenerator();
		String str = (String) bsg.generate(null);
		assertNotNull(str);
		assertTrue(str.length()>0);
	}
	
	@Test
	public void ifConstraintsAreSentPassedGeneratorWillJustPassItOnIfNextGeneratorIsPresent() {
		BasicStringGenerator bsg = new BasicStringGenerator();
		Generator anotherGen = mock(Generator.class);
		List<Annotation> constraints = mock(List.class);
		when(constraints.size()).thenReturn(1);
		String value = "value";
		when(anotherGen.generate(constraints)).thenReturn(value);
		bsg.setNext(anotherGen);
		String output = (String)bsg.generate(constraints);
		assertEquals(value,output);
		verify(anotherGen).generate(constraints);
	}
	
	@Test
	public void ifConstraintsAreSentPassedGeneratorWillReturnNullIfThereIsNoNextGeneratorPresent() {
		BasicStringGenerator bsg = new BasicStringGenerator();
		List<Annotation> constraints = mock(List.class);
		when(constraints.size()).thenReturn(1);
		String output = (String)bsg.generate(constraints);
		assertNull(output);
	}

}
