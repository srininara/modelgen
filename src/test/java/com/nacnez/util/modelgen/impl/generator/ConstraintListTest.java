package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class ConstraintListTest {

	Mirror mirror = new Mirror();

	@Test
	public void testConstraintListBasics() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1, constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList cl = new ConstraintList(constraints);
		assertEquals(constraints, cl.getInternalList());
		assertTrue(cl.contains(Size.class));
		Size constraint = (Size)cl.get(Size.class);
		assertEquals(15,constraint.maxSize());
	}

	private List<Annotation> getConstraints(String methodName) {
		return mirror.on(PersonContract.class).reflectAll().annotations()
				.atMethod(methodName).withArgs(String.class);
	}

}
