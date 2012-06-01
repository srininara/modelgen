package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class SizedStringGeneratorTest {

	Mirror mirror = new Mirror();
	
	@Test
	public void testGenerationHappyCase() {
		SizedStringGenerator ssg = spy(new SizedStringGenerator());
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		Generator anotherGen = mock(Generator.class);
		ConstraintList constraintList = mock(ConstraintList.class);
		when(ssg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(true);
		when(constraintList.get(Size.class)).thenReturn(constraints.get(0));
		int size = ((Size)constraints.get(0)).maxSize();
		String str = (String) ssg.generate(constraints);
		assertNotNull(str);
		assertEquals(size, str.length());
		
	}
	
	@Test
	public void ifPassedConstraintIsNotSizeGeneratorWillJustPassItOnIfNextGeneratorIsPresent() {
		SizedStringGenerator ssg = spy(new SizedStringGenerator());
		Generator anotherGen = mock(Generator.class);
		List<Annotation> constraints = mock(List.class);
		ConstraintList constraintList = mock(ConstraintList.class);
		when(ssg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(false);
		String value = "value";
		when(anotherGen.generate(constraints)).thenReturn(value);
		ssg.setNext(anotherGen);
		String output = (String)ssg.generate(constraints);
		verify(ssg).convert(constraints);
		verify(constraintList).contains(Size.class);
		verify(anotherGen).generate(constraints);
		assertEquals(value,output);
	}
	
	private List<Annotation> getConstraints(String methodName) {
		return mirror.on(PersonContract.class).reflectAll().annotations().atMethod(methodName).withArgs(String.class);
	}

	@Test
	public void ifPassedConstraintIsNotSizeAndNextGeneratorIsNotPresentGeneratorWillReturnNull() {
		SizedStringGenerator ssg = spy(new SizedStringGenerator());
		List<Annotation> constraints = mock(List.class);
		ConstraintList constraintList = mock(ConstraintList.class);
		when(ssg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(false);
		String value = null;
		String output = (String)ssg.generate(constraints);
		verify(ssg).convert(constraints);
		verify(constraintList).contains(Size.class);
		assertEquals(value,output);
	}
	
	@Test
	public void testConversion() {
  		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		SizedStringGenerator ssg = new SizedStringGenerator();
		ConstraintList cl = ssg.convert(constraints);
		assertEquals(constraints,cl.getInternalList());
	}

}
