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
	
	@Before
	public void setup() {
		cd = new ContractDigestImpl<Person>();
	}
	
	
	@Test
	public void digestingASimpleClassWhichHasASingleStringAttribute() {
	}


}
