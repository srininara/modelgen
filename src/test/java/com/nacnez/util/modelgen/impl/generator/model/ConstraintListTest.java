package com.nacnez.util.modelgen.impl.generator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.rules.Alphabetic;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;
import com.nacnez.util.modelgen.impl.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class ConstraintListTest {

	Mirror mirror = new Mirror();

	@Test
	public void testConstraintListBasics() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1, constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList cl = new ConstraintList(constraints);
		assertTrue(cl.contains(Size.class));
		Size constraint = (Size)cl.get(Size.class);
		assertEquals(15,constraint.maxSize());
	}
	
	@Test
	public void ifConstraintDoesNotExistInConstraintListThenContainsWillReturnFalse() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1, constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList cl = new ConstraintList(constraints);
		assertFalse(cl.contains(FromList.class));
	}

	@Test
	public void ifConstraintDoesNotExistInConstraintListThenGetWillReturnNull() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1, constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList cl = new ConstraintList(constraints);
		assertEquals(null,cl.get(FromList.class));
	}
	
	@Test
	public void ifThereAreMoreThanOneConstraintsInTheListThenTheConstraintListStillWorksAsExpected() {
		List<Annotation> constraints = getConstraints("setFirstName");
		assertEquals(2, constraints.size());
		ConstraintList cl = new ConstraintList(constraints);
		
		assertTrue(cl.contains(Size.class));
		assertTrue(cl.contains(Alphabetic.class));
		assertFalse(cl.contains(Alphanumeric.class));
		
		Size constraint = (Size)cl.get(Size.class);
		assertEquals(35,constraint.maxSize());
	}
	
	@Test
	public void ifConstraintListIsBackedByANonEmptyListOfConstraintsThenIsBackedReturnsTrue() {
		List<Annotation> constraints = getConstraints("setCreditCardNumber");
		assertEquals(1, constraints.size());
		assertTrue(constraints.get(0).annotationType().equals(Size.class));
		ConstraintList cl = new ConstraintList(constraints);
		assertTrue(cl.isBacked());
	}
	
	@Test
	public void ifConstraintListIsBackedByNullThenIsBackedReturnsFalse() {
		ConstraintList cl = new ConstraintList(null);
		assertFalse(cl.isBacked());
	}

	@Test
	public void ifConstraintListIsBackedByEmptyListThenIsBackedReturnsFalse() {
		ConstraintList cl = new ConstraintList(new ArrayList<Annotation>());
		assertFalse(cl.isBacked());
	}

	private List<Annotation> getConstraints(String methodName) {
		return mirror.on(PersonContract.class).reflectAll().annotations()
				.atMethod(methodName).withArgs(String.class);
	}

}
