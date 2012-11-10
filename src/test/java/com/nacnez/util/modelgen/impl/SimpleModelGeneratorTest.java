package com.nacnez.util.modelgen.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.GeneratorFactory;
import com.nacnez.util.modelgen.ModelGenerator;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockObject;

public class SimpleModelGeneratorTest {

	ModelGenerator<SimpleMockObject> smg;

	@Before
	public void setup() {
		// creating the SUT/CUT
		smg = GeneratorFactory.get();
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAnEmptyModelWhenContractIsNotProvided() {
		Collection<SimpleMockObject> c = smg.make(1)
				.instancesOf(SimpleMockObject.class).andProvideAsCollection();
		assertEquals(1, c.size());
		SimpleMockObject smo = c.iterator().next();
		assertEmptySimpleMockObject(smo);
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyEmptyModelsAreRequestedWhenContractIsNotProvided() {
		Collection<SimpleMockObject> c = smg.make(10)
				.instancesOf(SimpleMockObject.class).andProvideAsCollection();
		assertEquals(10, c.size());
		for (SimpleMockObject smo : c) {
			assertEmptySimpleMockObject(smo);
		}
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAModelWhenContractIsProvided() {
		Collection<SimpleMockObject> c = smg.make(1)
				.instancesWith(SimpleMockGenerationContract.class)
				.andProvideAsCollection();
		assertEquals(1, c.size());
		SimpleMockObject smo = c.iterator().next();
		assertFilledSimpleMockObject(smo);
	}

	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyModelsAreRequestedWhenContractIsProvided() {
		Collection<SimpleMockObject> c = smg.make(10)
				.instancesWith(SimpleMockGenerationContract.class)
				.andProvideAsCollection();
		assertEquals(10, c.size());
		for (SimpleMockObject smo : c) {
			assertFilledSimpleMockObject(smo);
		}
	}
	
	@Test
	public void modelGeneratorShouldBeAbleToCreateEmptyModelsAsRequestedAndFillAProvidedCollection() {
		Collection<SimpleMockObject> c = new ArrayList<SimpleMockObject>();
		smg.make(10).instancesOf(SimpleMockObject.class).andFillUpThis(c);
		assertEquals(10, c.size());
		for (SimpleMockObject smo : c) {
			assertEmptySimpleMockObject(smo);
		}
	}
	
	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyModelsAreRequestedWhenContractIsProvidedAndFillAProvidedCollection() {
		Collection<SimpleMockObject> c = new ArrayList<SimpleMockObject>();
		smg.make(10).instancesWith(SimpleMockGenerationContract.class).andFillUpThis(c);
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
