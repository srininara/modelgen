package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.SimpleDoubleGenerationMockContract;
import com.nacnez.util.modelgen.generator.rules.DoubleLimit;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class LimitBoundDoubleGeneratorTest {

	Mirror mirror = new Mirror();
	
	private void testGeneration(String methodName) {
		Injector injector = Guice.createInjector(new ModelGenModule());

		LimitBoundDoubleGenerator ldg = spy(injector.getInstance(LimitBoundDoubleGenerator.class));
		List<Annotation> constraints = getConstraints(methodName);
		assertEquals(1,constraints.size());
		Class<? extends Annotation> constraint1 = constraints.get(0).annotationType();
		assertTrue(constraint1.equals(DoubleLimit.class));

		ConstraintList constraintList = mock(ConstraintList.class);
		when(ldg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(DoubleLimit.class)).thenReturn(true);
		when(constraintList.get(DoubleLimit.class)).thenReturn(constraints.get(0));
		
		String lowLimit = ((DoubleLimit)constraints.get(0)).lowLimit();
		String highLimit = ((DoubleLimit)constraints.get(0)).highLimit();
		
		Double doutput = (Double) ldg.generate(constraints);
		verify(ldg).convert(constraints);
		verify(constraintList).contains(DoubleLimit.class);
		verify(constraintList).get(DoubleLimit.class);
		assertNotNull(doutput);
		
		assertTrue(doutput.compareTo(new Double(lowLimit))>=0);
		assertTrue(doutput.compareTo(new Double(highLimit))<=0);

	}

	@Test
	public void testBothLimitCase() {
		testGeneration("setMockBothLimitDouble");
	}
	
	@Test
	public void testNegativeCase() {
		testGeneration("setMockNegativeDouble");
	}

	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(SimpleDoubleGenerationMockContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(Double.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(SimpleDoubleGenerationMockContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}
	
	
	
}
