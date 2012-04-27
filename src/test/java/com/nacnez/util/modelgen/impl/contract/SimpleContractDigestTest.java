package com.nacnez.util.modelgen.impl.contract;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import net.vidageek.mirror.dsl.Mirror;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.Person;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.*;

public class SimpleContractDigestTest {

	private SimpleContractDigest<Person> scd;
	
	private Mirror mirror = new Mirror();
	
	private String output = "SomeString";
	
	@Before
	public void setup() {
		scd = new SimpleContractDigest<Person>();
		scd.setGenerator(mock(DataGenerator.class));
		//when 
		
	}

	@Test
	public void basicTest() {
		scd.set(mirror.on(PersonContract.class).reflectAll().methods().get(0));
		Person model = new Person();
		scd.fill(model);
		assertNotNull(model.getName());
	}

}
