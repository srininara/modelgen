package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class AlphabeticStringGeneratorTest {

	Mirror mirror = new Mirror();

	@Test
	public void testHappyCase() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		AlphabeticStringGenerator asg = spy(injector.getInstance(AlphabeticStringGenerator.class));

		List<Annotation> constraints = getConstraints("setMockAlphabeticString");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Alphabetic.class));
		Generator anotherGen = mock(Generator.class);
		ConstraintList constraintList = mock(ConstraintList.class);
		when(asg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Alphabetic.class)).thenReturn(true);
		String str = (String) asg.generate(constraints);
		verify(asg).convert(constraints);
		verify(constraintList).contains(Alphabetic.class);
		verify(constraintList,never()).get(Alphabetic.class);
		assertNotNull(str);
		assertTrue(StringUtils.isAlpha(str));
		
	}

	// Note: Don't think other cases (passed constraint is not Alphabetic and next generator unavailable)
	// have to be tested again. They are already covered in other tests.
	
	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(SimpleMockGenerationContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(String.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(SimpleMockGenerationContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}

}
