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
import java.util.Date;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.SimpleDateGenerationMockContract;
import com.nacnez.util.modelgen.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.rules.DateLimit;

public class LimitBoundDateGeneratorTest {
	Mirror mirror = new Mirror();
	
	private void testGeneration(String methodName) {
		Injector injector = Guice.createInjector(new ModelGenModule());

		LimitBoundDateGenerator lbdg = spy(injector.getInstance(LimitBoundDateGenerator.class));
		List<Annotation> constraints = getConstraints(methodName);
		assertEquals(1,constraints.size());
		Class<? extends Annotation> constraint1 = constraints.get(0).annotationType();
		assertTrue(constraint1.equals(DateLimit.class));

		ConstraintList constraintList = mock(ConstraintList.class);
		when(lbdg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(DateLimit.class)).thenReturn(true);
		when(constraintList.get(DateLimit.class)).thenReturn(constraints.get(0));
		
		String lowLimitS = ((DateLimit)constraints.get(0)).lowLimit();
		String highLimitS = ((DateLimit)constraints.get(0)).highLimit();
		String format = ((DateLimit)constraints.get(0)).format();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		
		Date lowLimit = formatter.parseDateTime(lowLimitS).toDateMidnight().toDate();
		Date highLimit = formatter.parseDateTime(highLimitS).toDateMidnight().toDate();
		
		Generator anotherGen = mock(Generator.class);
		Date dateOutput = (Date) lbdg.generate(constraints);
		verify(lbdg).convert(constraints);
		verify(constraintList).contains(DateLimit.class);
		verify(constraintList).get(DateLimit.class);
		assertNotNull(dateOutput);
		
		assertTrue(dateOutput.compareTo(lowLimit)>=0);
		assertTrue(dateOutput.compareTo(highLimit)<=0);

	}

	@Test
	public void testBothLimitCase() {
		testGeneration("setMockBothLimitDate");
	}
	
	@Test
	public void testDifferentFormat() {
		testGeneration("setMockDiffFormatDate");
	}


	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(SimpleDateGenerationMockContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(Date.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(SimpleDateGenerationMockContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}
	
}
