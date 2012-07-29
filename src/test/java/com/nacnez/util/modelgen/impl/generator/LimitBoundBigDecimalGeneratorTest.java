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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.SimpleBigDecimalGenerationMockContract;
import com.nacnez.util.modelgen.generator.rules.DecimalLimit;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class LimitBoundBigDecimalGeneratorTest {

	Mirror mirror = new Mirror();
	
	private void testGeneration(String methodName) {
		Injector injector = Guice.createInjector(new ModelGenModule());

		LimitBoundBigDecimalGenerator lbig = spy(injector.getInstance(LimitBoundBigDecimalGenerator.class));
		List<Annotation> constraints = getConstraints(methodName);
		assertEquals(1,constraints.size());
		Class<? extends Annotation> constraint1 = constraints.get(0).annotationType();
		assertTrue(constraint1.equals(DecimalLimit.class));

		ConstraintList constraintList = mock(ConstraintList.class);
		when(lbig.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(DecimalLimit.class)).thenReturn(true);
		when(constraintList.get(DecimalLimit.class)).thenReturn(constraints.get(0));
		
		String lowLimit = ((DecimalLimit)constraints.get(0)).lowLimit();
		String highLimit = ((DecimalLimit)constraints.get(0)).highLimit();
		int scale = ((DecimalLimit)constraints.get(0)).scale();
		
		BigDecimal bigDoutput = (BigDecimal) lbig.generate(constraints);
		verify(lbig).convert(constraints);
		verify(constraintList).contains(DecimalLimit.class);
		verify(constraintList).get(DecimalLimit.class);
		assertNotNull(bigDoutput);
		
		assertTrue(bigDoutput.compareTo(new BigDecimal(lowLimit))>=0);
		assertTrue(bigDoutput.compareTo(new BigDecimal(highLimit))<=0);
		assertTrue(bigDoutput.scale()==scale);

	}

	@Test
	public void testBothLimitCase() {
		testGeneration("setMockBothLimitBigDecimal");
	}
	
	@Test
	public void testNegativeCase() {
		testGeneration("setMockNegativeBigDecimal");
	}

	@Test
	public void testDifferentScale() {
		testGeneration("setMockDiffScaleBigDecimal");
	}


	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(SimpleBigDecimalGenerationMockContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(BigDecimal.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(SimpleBigDecimalGenerationMockContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}
	
	
	
}
