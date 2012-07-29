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
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class ListOfStringGeneratorTest {

	Mirror mirror = new Mirror();

	@Test
	public void testHappyCase() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		StringFromListGenerator sflg = spy(injector.getInstance(StringFromListGenerator.class));

		List<Annotation> constraints = getConstraints("setMockStringFromList");
		assertEquals(1,constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(FromList.class));
		ConstraintList constraintList = mock(ConstraintList.class);
		when(sflg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(FromList.class)).thenReturn(true);
		when(constraintList.get(FromList.class)).thenReturn(constraints.get(0));
		String str = (String) sflg.generate(constraints);
		verify(sflg).convert(constraints);
		verify(constraintList).contains(FromList.class);
		verify(constraintList).get(FromList.class);
		assertNotNull(str);
		String[] fromList = {"Mock1","Mock2","Mock3"};
		assertTrue(Arrays.asList(fromList).contains(str));
		
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
