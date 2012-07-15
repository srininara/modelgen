package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;
import org.mockito.InOrder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class SizedStringDecoratorTest {

	Mirror mirror = new Mirror();
	
	@Test
	public void testGenerationHappyCase() {
		StringGenerator mockBackingGenerator = mock(StringGenerator.class);
		SizedStringDecorator ssd = spy(new SizedStringDecorator(mockBackingGenerator));
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
//		Generator anotherGen = mock(Generator.class);
		ConstraintList constraintList = mock(ConstraintList.class);
		when(ssd.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(true);
		when(constraintList.get(Size.class)).thenReturn(constraints.get(0));
		when(mockBackingGenerator.applicable(constraintList)).thenReturn(true);
		String mockString = new String("mock");
		int size = ((Size)constraints.get(0)).maxSize();
		doReturn(size).when(ssd).getLength(constraintList);
		when(mockBackingGenerator.doGenerate(constraintList,size)).thenReturn(mockString);
		String str = (String) ssd.generate(constraints);
		InOrder inOrder = inOrder(mockBackingGenerator,ssd);
		inOrder.verify(ssd).applicable(constraintList);
		inOrder.verify(mockBackingGenerator).applicable(constraintList);
		inOrder.verify(ssd).getLength(constraintList);
		inOrder.verify(ssd).doGenerate(constraintList,size);
		inOrder.verify(mockBackingGenerator).doGenerate(constraintList,size);
		assertNotNull(str);
		assertEquals(mockString, str);
		
	}
	
	@Test
	public void testGetLength() {
		StringGenerator mockBackingGenerator = mock(StringGenerator.class);
		SizedStringDecorator ssd = spy(new SizedStringDecorator(mockBackingGenerator));
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList constraintList = mock(ConstraintList.class);
		when(ssd.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(true);
		when(constraintList.get(Size.class)).thenReturn(constraints.get(0));
		int size = ((Size)constraints.get(0)).maxSize();
		assertEquals(size,ssd.getLength(constraintList));
		
	}
	
	
	@Test
	public void ifPassedConstraintIsNotSizeGeneratorWillJustPassItOnIfNextGeneratorIsPresent() {
		SizedStringDecorator ssg = spy(new SizedStringDecorator(new BasicStringGenerator()));
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
	
	@Test
	public void ifPassedConstraintIsNotSizeAndNextGeneratorIsNotPresentGeneratorWillReturnNull() {
		SizedStringDecorator ssg = spy(new SizedStringDecorator(new BasicStringGenerator()));
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
		SizedStringDecorator ssg = new SizedStringDecorator(new BasicStringGenerator());
		ConstraintList cl = ssg.convert(constraints);
		assertTrue(cl.contains(Size.class));
		assertEquals(constraints.get(0),cl.get(Size.class));
	}

	
	@Test
	public void ifConstraintIsSizeAndPassedAsPartOfParameterReferenceThenItWillStillWorkProperly() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		BasicStringGenerator bsg = injector.getInstance(BasicStringGenerator.class);
		SizedStringDecorator ssg = spy(new SizedStringDecorator(bsg));
		List<Annotation> constraints = getConstraints("setMockSizedStringParam");
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

	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(PersonContract.class).reflectAll().annotations().atMethod(methodName).withArgs(String.class);
		if (constraints==null || constraints.size()==0) {
			Method m = mirror.on(PersonContract.class).reflect().method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}


}
