package com.nacnez.util.modelgen.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockObject;

public class SimpleModelGeneratorTest {

	// SimpleModelGenerator<Person> smg;
	SimpleModelGenerator<SimpleMockObject> smg;

	@Before
	public void setup() {
		// creating the SUT/CUT
		// smg = new SimpleModelGenerator<Person>();
		smg = new SimpleModelGenerator<SimpleMockObject>();

	}

	// The below two tests could be reinstated after Completing the rest of
	// generation stuff.
	// @Test
	// public void
	// modelGeneratorShouldBeAbleToCreateAnEmptyModelWhenContractIsNotProvided()
	// {
	// Collection<Person> c =
	// smg.make(1).instancesOf(Person.class).andProvideAsCollection();
	// assertEquals(1,c.size());
	// }
	//
	// @Test
	// public void
	// modelGeneratorShouldBeAbleToCreateAsManyEmptyModelsAreRequestedWhenContractIsNotProvided()
	// {
	// Collection<Person> c =
	// smg.make(10).instancesOf(Person.class).andProvideAsCollection();
	// assertEquals(10,c.size());
	// }

	@Test
	public void modelGeneratorShouldBeAbleToCreateAnEmptyModelWhenContractIsNotProvided() {
		Collection<SimpleMockObject> c = smg.make(1)
				.instancesOf(SimpleMockObject.class)
				.andProvideAsCollection();
		assertEquals(1, c.size());
		SimpleMockObject smo = c.iterator().next();
		assertEmptySimpleMockObject(smo);
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyEmptyModelsAreRequestedWhenContractIsNotProvided() {
		Collection<SimpleMockObject> c = smg.make(10)
				.instancesOf(SimpleMockObject.class)
				.andProvideAsCollection();
		assertEquals(10, c.size());
		for (SimpleMockObject smo : c) {
			assertEmptySimpleMockObject(smo);
		}
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAnEmptyModelWhenContractIsProvided() {
		Collection<SimpleMockObject> c = smg.make(1)
				.instancesOf(SimpleMockObject.class)
				.with(SimpleMockGenerationContract.class)
				.andProvideAsCollection();
		assertEquals(1, c.size());
		SimpleMockObject smo = c.iterator().next();
		assertFilledSimpleMockObject(smo);
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyEmptyModelsAreRequestedWhenContractIsProvided() {
		Collection<SimpleMockObject> c = smg.make(10)
				.instancesOf(SimpleMockObject.class)
				.with(SimpleMockGenerationContract.class)
				.andProvideAsCollection();
		assertEquals(10, c.size());
		for (SimpleMockObject smo : c) {
			assertFilledSimpleMockObject(smo);
		}
	}

	void assertFilledSimpleMockObject(SimpleMockObject smo) {
		assertNotNull(smo.getMockUnSizedString());
		assertNotNull(smo.getMockSizedString());
		assertEquals(32, smo.getMockSizedString().length());

	}

	void assertEmptySimpleMockObject(SimpleMockObject smo) {
		assertNull(smo.getMockUnSizedString());
		assertNull(smo.getMockSizedString());
	}
	
}
