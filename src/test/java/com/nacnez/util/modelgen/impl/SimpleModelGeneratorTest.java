package com.nacnez.util.modelgen.impl;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.Person;


public class SimpleModelGeneratorTest {

	SimpleModelGenerator<Person> smg;
	
	@Before
	public void setup() {
		// creating the SUT/CUT
		smg = new SimpleModelGenerator<Person>();
	}
	
	@Test
	public void modelGeneratorShouldBeAbleToCreateAnEmptyModelWhenContractIsNotProvided() {
		Collection<Person> c = smg.make(1).instancesOf(Person.class).andProvideAsCollection();
		assertEquals(1,c.size());
	}
	
	@Test
	public void modelGeneratorShouldBeAbleToCreateAsManyEmptyModelsAreRequestedWhenContractIsNotProvided() {
		Collection<Person> c = smg.make(10).instancesOf(Person.class).andProvideAsCollection();
		assertEquals(10,c.size());
	}
}
