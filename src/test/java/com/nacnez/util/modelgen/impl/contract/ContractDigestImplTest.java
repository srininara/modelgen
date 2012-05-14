package com.nacnez.util.modelgen.impl.contract;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.Person;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.impl.generator.DataGenerator;
import com.nacnez.util.modelgen.impl.generator.rules.FromList;

public class ContractDigestImplTest {

	private ContractDigestImpl cd;
	
	private Mirror mirror = new Mirror();
	
	@Before
	public void setup() {
		cd = new ContractDigestImpl();
	}
	
	
	@Test
	public void digestingASimpleClassWhichHasASingleStringAttribute() {
		
		Person p = new Person();
		cd.digest(PersonContract.class).fill(p);
		
		assertEquals(32,p.getFirstName().length());
		assertTrue(StringUtils.isAlpha(p.getFirstName()));
		
		assertEquals(15,p.getCreditCardNumber().length());
		
		assertEquals(15,p.getPAN().length());
		assertTrue(StringUtils.isAlphanumeric(p.getPAN()));
		
		
		String[] fromList = {"Single","Married","Divorced"};
		assertTrue(Arrays.asList(fromList).contains(p.getMaritalStatus()));
		
	}


}
