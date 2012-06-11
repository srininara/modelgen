package com.nacnez.util.modelgen.impl.contract;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.Person;
import com.nacnez.util.modelgen.exampleModels.PersonContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockObject;

// This is an integration (and a classic style) test

public class ContractDigestImplTest {

	private ContractDigestImpl cd;
	
	private Mirror mirror = new Mirror();
	
	@Before
	public void setup() {
		cd = new ContractDigestImpl();
	}
	

	// Have to reinstate this once I finish refactoring the rest of string generation and full integer generation
//	@Test
//	public void digestingAndPopulatingASimpleClassWhichHasStringAndIntegerAttributes() {
//		
//		Person p = new Person();
//		cd.digest(PersonContract.class).fill(p);
//		
//		assertEquals(32,p.getFirstName().length());
//		assertTrue(StringUtils.isAlpha(p.getFirstName()));
//		
//		assertEquals(15,p.getCreditCardNumber().length());
//		
//		assertEquals(15,p.getPAN().length());
//		assertTrue(StringUtils.isAlphanumeric(p.getPAN()));
//		
//		
//		String[] fromList = {"Single","Married","Divorced"};
//		assertTrue(Arrays.asList(fromList).contains(p.getMaritalStatus()));
//
//		assertTrue(p.getId()<=1000000);
//		assertTrue(p.getId()>0);
//		
//		assertTrue(p.getCreditAmount()<0);
//		
//		assertTrue(p.getLoanAmount()<0);
//		assertTrue(p.getLoanAmount()>= -1000000);
//	}
	
	@Test
	public void digestingAndPopulatingASimpleClassWhichHasStringsAttributesOnly() {
		SimpleMockObject smo = new SimpleMockObject();
		cd.digest(SimpleMockGenerationContract.class).fill(smo);
		assertNotNull(smo.getMockUnSizedString());
		assertNotNull(smo.getMockSizedString());
		assertEquals(32,smo.getMockSizedString().length());
	}
}
