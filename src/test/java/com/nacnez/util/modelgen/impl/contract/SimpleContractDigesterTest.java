package com.nacnez.util.modelgen.impl.contract;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.PersonContract;

public class SimpleContractDigesterTest {

	SimpleContractDigester<PersonContract> scd;
	
	@Before
	public void setup() {
		scd = new SimpleContractDigester<PersonContract>();
	}

	@Test
	public void basicTest() {
		ContractDigest<PersonContract> cd = scd.digest(PersonContract.class);
		String methodName = "setName";
		assertTrue(cd.info().contains(methodName));
	}

}
