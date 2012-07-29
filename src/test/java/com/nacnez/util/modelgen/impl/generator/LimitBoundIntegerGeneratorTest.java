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
import com.nacnez.util.modelgen.exampleModels.SimpleIntegerGenerationMockContract;
import com.nacnez.util.modelgen.generator.rules.Limit;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class LimitBoundIntegerGeneratorTest {

	Mirror mirror = new Mirror();
	
	private void testGeneration(String methodName) {
		Injector injector = Guice.createInjector(new ModelGenModule());

		LimitBoundIntegerGenerator lbig = spy(injector.getInstance(LimitBoundIntegerGenerator.class));
		List<Annotation> constraints = getConstraints(methodName);
		assertEquals(1,constraints.size());
		Class<? extends Annotation> constraint1 = constraints.get(0).annotationType();
		assertTrue(constraint1.equals(Limit.class));

		ConstraintList constraintList = mock(ConstraintList.class);
		when(lbig.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Limit.class)).thenReturn(true);
		when(constraintList.get(Limit.class)).thenReturn(constraints.get(0));
		
		int lowLimit = ((Limit)constraints.get(0)).lowLimit();
		int highLimit = ((Limit)constraints.get(0)).highLimit();
		
		Integer intr = (Integer) lbig.generate(constraints);
		verify(lbig).convert(constraints);
		verify(constraintList).contains(Limit.class);
		verify(constraintList).get(Limit.class);
		assertNotNull(intr);
		assertTrue(intr>=lowLimit);
		assertTrue(intr<=highLimit);
		
	}
	
	@Test
	public void testHighLimitCase() {
		testGeneration("setMockHighLimitInteger");
	}
	
	@Test
	public void testLowLimitCase() {
		testGeneration("setMockLowLimitInteger");
	}

	@Test
	public void testBothLimitCase() {
		testGeneration("setMockBothLimitInteger");
	}
	
	@Test
	public void testSimpleCase() {
		testGeneration("setMockDefaultLimitInteger");
	}
	
	@Test
	public void testNegativeCase() {
		testGeneration("setMockNegativeInteger");
	}
	
	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(SimpleIntegerGenerationMockContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(Integer.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(SimpleIntegerGenerationMockContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}

}
