package com.nacnez.util.modelgen.impl.contract;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import net.vidageek.mirror.dsl.Mirror;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.CompoundMockObject;
import com.nacnez.util.modelgen.exampleModels.CompoundMockObjectContract;
import com.nacnez.util.modelgen.exampleModels.SimpleIntegerGenerationMockContract;
import com.nacnez.util.modelgen.exampleModels.SimpleIntegerGenerationMockObject;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockObject;
import com.nacnez.util.modelgen.factory.ModelGenModule;

// This is an integration (and a classic style) test

public class ContractDigestImplTest {

	private ContractDigest cd;
	
	private Mirror mirror = new Mirror();
	
	@Before
	public void setup() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		cd =injector.getInstance(ContractDigestImpl.class);
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
		SimpleMockObject smo = (SimpleMockObject) cd.digest(SimpleMockGenerationContract.class).make();
		assertSmo(smo);
	}

	@Test
	public void digestingAndPopulatingASimpleClassWhichHasIntegerAttributesOnly() {
		SimpleIntegerGenerationMockObject sigmo = (SimpleIntegerGenerationMockObject) cd.digest(SimpleIntegerGenerationMockContract.class).make();
		assertSigmo(sigmo);
	}

	@Test
	public void digestingAndPopulatingACompoundClassWhichHasOtherContracts() {
		CompoundMockObject cmo = (CompoundMockObject) cd.digest(CompoundMockObjectContract.class).make();
		
		assertNotNull(cmo.getCompoundId());
		assertTrue(StringUtils.isAlphanumeric(cmo.getCompoundId()));

		assertNotNull(cmo.getSigmo());
		assertSigmo(cmo.getSigmo());
		
		assertNotNull(cmo.getSmo());
		assertSmo(cmo.getSmo());
	}

	private void assertSigmo(SimpleIntegerGenerationMockObject sigmo) {
		assertNotNull(sigmo.getMockNoLimitInteger());
		assertTrue(sigmo.getMockNoLimitInteger()>=0);
		
		assertNotNull(sigmo.getMockDefaultLimitInteger());
		assertTrue(sigmo.getMockDefaultLimitInteger()>Integer.MIN_VALUE && sigmo.getMockDefaultLimitInteger()<Integer.MAX_VALUE);
		
		assertNotNull(sigmo.getMockHighLimitInteger());
		assertNotNull(sigmo.getMockHighLimitInteger()<=1000000);
		
		assertNotNull(sigmo.getMockLowLimitInteger());
		assertNotNull(sigmo.getMockLowLimitInteger()>=1000001);
		
		assertNotNull(sigmo.getMockBothLimitInteger());
		assertTrue(sigmo.getMockBothLimitInteger()>=10000 && sigmo.getMockDefaultLimitInteger()<=20000);
		
		assertNotNull(sigmo.getMockNegativeInteger());
		assertTrue(sigmo.getMockNegativeInteger()<=-1);
	}

	private void assertSmo(SimpleMockObject smo) {
		assertNotNull(smo.getMockUnSizedString());

		assertNotNull(smo.getMockSizedString());
		assertEquals(32,smo.getMockSizedString().length());

		assertNotNull(smo.getMockedSizedStringParam());
		assertEquals(50,smo.getMockedSizedStringParam().length());

		assertNotNull(smo.getMockAlphabeticString());
		assertTrue(StringUtils.isAlpha(smo.getMockAlphabeticString()));

		assertNotNull(smo.getMockAlphabeticSizedString());
		assertTrue(StringUtils.isAlpha(smo.getMockAlphabeticSizedString()));
		assertEquals(40,smo.getMockAlphabeticSizedString().length());

		assertNotNull(smo.getMockAlphanumericString());
		assertTrue(StringUtils.isAlphanumeric(smo.getMockAlphanumericString()));

		assertNotNull(smo.getMockSizedAlphanumericString());
		assertTrue(StringUtils.isAlphanumeric(smo.getMockSizedAlphanumericString()));
		assertEquals(40,smo.getMockSizedAlphanumericString().length());

		String[] fromList = {"Mock1","Mock2","Mock3"};
		assertNotNull(smo.getMockStringFromList());
		assertTrue(Arrays.binarySearch(fromList, smo.getMockStringFromList())>=0);
	}
}
