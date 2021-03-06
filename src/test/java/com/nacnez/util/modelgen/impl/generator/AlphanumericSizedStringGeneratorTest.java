package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.generator.rules.Size;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;

public class AlphanumericSizedStringGeneratorTest {

	Mirror mirror = new Mirror();

	@Test
	public void testHappyCase() {
		Injector injector = Guice.createInjector(new ModelGenModule());

		AlphanumericStringGenerator assg = spy(injector.getInstance(AlphanumericStringGenerator.class));
		List<Annotation> constraints = getConstraints("setPAN");
		assertEquals(2,constraints.size());
		Class<? extends Annotation> constraint1 = constraints.get(0).annotationType();
		Class<? extends Annotation> constraint2 = constraints.get(1).annotationType(); 
		assertTrue(constraint1.equals(Alphanumeric.class) || constraint1.equals(Size.class));
		assertTrue(constraint2.equals(Alphanumeric.class) || constraint2.equals(Size.class));

		ConstraintList constraintList = mock(ConstraintList.class);
		when(assg.convert(constraints)).thenReturn(constraintList);
		when(constraintList.contains(Size.class)).thenReturn(true);
		when(constraintList.contains(Alphanumeric.class)).thenReturn(true);
		when(constraintList.get(Size.class)).thenReturn((constraint1.equals(Size.class))?constraints.get(0):constraints.get(1));
		
		int size = ((constraint1.equals(Size.class))?((Size)constraints.get(0)).maxSize():((Size)constraints.get(1)).maxSize());

		doReturn(size).when(assg).getLength(constraintList);

		String str = (String) assg.generate(constraints);
		verify(assg).convert(constraints);
		verify(constraintList).contains(Alphanumeric.class);
		verify(constraintList,never()).get(Alphanumeric.class);
//		verify(constraintList).contains(Size.class);
		assertNotNull(str);
		assertTrue(StringUtils.isAlphanumeric(str));
		assertEquals(size,str.length());
		
	}

	// Note: Don't think other cases (passed constraint is not Alphabetic and next generator unavailable)
	// have to be tested again. They are already covered in other tests.
	
	private List<Annotation> getConstraints(String methodName) {
		List<Annotation> constraints = mirror.on(PersonContract.class)
				.reflectAll().annotations().atMethod(methodName)
				.withArgs(String.class);
		if (constraints == null || constraints.size() == 0) {
			Method m = mirror.on(PersonContract.class).reflect()
					.method(methodName).withAnyArgs();
			constraints = Arrays.asList(m.getParameterAnnotations()[0]);
		}
		return constraints;
	}

}
