package com.nacnez.util.modelgen.impl.contract;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.Person;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.DataGenerator;

public class ContractDigestImplTest {

	private ContractDigestImpl<Person> cd;
	
	private Mirror mirror = new Mirror();
	
	private String output = "SomeString";
	
	@Before
	public void setup() {
		cd = new ContractDigestImpl<Person>();
	}
	
	
	@Test
	public void digestingASimpleClassWhichHasASingleStringAttribute() {
		Collection<DataGenerator<? extends Object>> internalGenerators = ((ContractDigestImpl<Person>)cd.digest(PersonContract.class)).getInternalGenerators();
		
		assertEquals(1,internalGenerators.size());
		DataGenerator<?> generator = internalGenerators.iterator().next();
		String output = (String)generator.generate();
		assertNotNull(output);
	}

//	@Test
//	public void basicTest() {
//		cd.set(mirror.on(PersonContract.class).reflectAll().methods().get(0));
//		Person model = new Person();
//		cd.fill(model);
//		assertNotNull(model.getName());
//	}

}
