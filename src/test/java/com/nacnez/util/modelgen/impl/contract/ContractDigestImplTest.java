package com.nacnez.util.modelgen.impl.contract;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.CompoundMockObject;
import com.nacnez.util.modelgen.exampleModels.CompoundMockObjectContract;
import com.nacnez.util.modelgen.exampleModels.SimpleBigDecimalGenerationMockContract;
import com.nacnez.util.modelgen.exampleModels.SimpleBigDecimalGenerationMockObject;
import com.nacnez.util.modelgen.exampleModels.SimpleDateGenerationMockContract;
import com.nacnez.util.modelgen.exampleModels.SimpleDateGenerationMockObject;
import com.nacnez.util.modelgen.exampleModels.SimpleDoubleGenerationMockContract;
import com.nacnez.util.modelgen.exampleModels.SimpleDoubleGenerationMockObject;
import com.nacnez.util.modelgen.exampleModels.SimpleIntegerGenerationMockContract;
import com.nacnez.util.modelgen.exampleModels.SimpleIntegerGenerationMockObject;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.exampleModels.SimpleMockObject;
import com.nacnez.util.modelgen.impl.factory.ModelGenModule;

// This is an integration (and a classic style) test

public class ContractDigestImplTest {

	private ContractDigest cd;

	@Before
	public void setup() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		cd = injector.getInstance(ContractDigestImpl.class);
	}

	// Have to reinstate this once I finish refactoring the rest of string
	// generation and full integer generation
	// @Test
	// public void
	// digestingAndPopulatingASimpleClassWhichHasStringAndIntegerAttributes() {
	//
	// Person p = new Person();
	// cd.digest(PersonContract.class).fill(p);
	//
	// assertEquals(32,p.getFirstName().length());
	// assertTrue(StringUtils.isAlpha(p.getFirstName()));
	//
	// assertEquals(15,p.getCreditCardNumber().length());
	//
	// assertEquals(15,p.getPAN().length());
	// assertTrue(StringUtils.isAlphanumeric(p.getPAN()));
	//
	//
	// String[] fromList = {"Single","Married","Divorced"};
	// assertTrue(Arrays.asList(fromList).contains(p.getMaritalStatus()));
	//
	// assertTrue(p.getId()<=1000000);
	// assertTrue(p.getId()>0);
	//
	// assertTrue(p.getCreditAmount()<0);
	//
	// assertTrue(p.getLoanAmount()<0);
	// assertTrue(p.getLoanAmount()>= -1000000);
	// }

	@Test
	public void digestingAndPopulatingASimpleClassWhichHasStringsAttributesOnly() {
		SimpleMockObject smo = (SimpleMockObject) cd.digest(
				SimpleMockGenerationContract.class).make();
		assertSmo(smo);
	}

	@Test
	public void digestingAndPopulatingASimpleClassWhichHasIntegerAttributesOnly() {
		SimpleIntegerGenerationMockObject sigmo = (SimpleIntegerGenerationMockObject) cd
				.digest(SimpleIntegerGenerationMockContract.class).make();
		assertSigmo(sigmo);
	}

	@Test
	public void digestingAndPopulatingASimpleClassWhichHasBigDecimalAttributesOnly() {
		SimpleBigDecimalGenerationMockObject sbdgmo = (SimpleBigDecimalGenerationMockObject) cd
				.digest(SimpleBigDecimalGenerationMockContract.class).make();
		assertSbdgmo(sbdgmo);
	}
	
	@Test
	public void digestingAndPopulatingASimpleClassWhichHasDoubleAttributesOnly() {
		SimpleDoubleGenerationMockObject sdgmo = (SimpleDoubleGenerationMockObject) cd
				.digest(SimpleDoubleGenerationMockContract.class).make();
		assertSdgmo(sdgmo);
	}

	@Test
	public void digestingAndPopulatingASimpleClassWhichHasDateAttributesOnly() {
		SimpleDateGenerationMockObject sdgmo = (SimpleDateGenerationMockObject) cd.digest(SimpleDateGenerationMockContract.class).make();
		assertSdgmo(sdgmo);
	}

	private void assertSdgmo(SimpleDateGenerationMockObject sdgmo) {
		assertDateOutput(sdgmo.getMockBothLimitDate(),"15-01-2010","15-01-2012","dd-mm-yyyy");
		assertDateOutput(sdgmo.getMockDiffFormatDate(),"01-Jan-2010","01-Jan-2012","dd-MMM-yyyy");

	}
	
	private void assertDateOutput(Date dateOutput,String lowLimitS,String highLimitS, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		Date lowLimit = formatter.parseDateTime(lowLimitS).toDateMidnight().toDate();
		
		Date highLimit = formatter.parseDateTime(highLimitS).toDateMidnight().toDate();
		
		assertNotNull(dateOutput);
		
		assertTrue(dateOutput.compareTo(lowLimit)>=0);
		assertTrue(dateOutput.compareTo(highLimit)<=0);
		

	}

	private void assertSbdgmo(SimpleBigDecimalGenerationMockObject sbdgmo) {
		assertBDoutput(sbdgmo.getMockBothLimitBigDecimal(),"20000002.22","40000002.43",2);
		assertBDoutput(sbdgmo.getMockNegativeBigDecimal(),"-20000002.22","-1.12",2);
		assertBDoutput(sbdgmo.getMockDiffScaleBigDecimal(),"10000.123","40000002.43",3);

	}


	private void assertSdgmo(SimpleDoubleGenerationMockObject sdgmo) {
		assertDoutput(sdgmo.getMockBothLimitDouble(),"20000002.22","40000002.43");
		assertDoutput(sdgmo.getMockNegativeDouble(),"-20000002.22","-1.12");

	}

	private void assertDoutput(Double doutput, String lowLimit,
			String highLimit) {
		assertNotNull(doutput);

		assertTrue(doutput.compareTo(new Double(lowLimit)) >= 0);
		assertTrue(doutput.compareTo(new Double(highLimit)) <= 0);
	}

	private void assertBDoutput(BigDecimal bigDoutput, String lowLimit,
			String highLimit, int scale) {
		assertNotNull(bigDoutput);

		assertTrue(bigDoutput.compareTo(new BigDecimal(lowLimit)) >= 0);
		assertTrue(bigDoutput.compareTo(new BigDecimal(highLimit)) <= 0);
		assertTrue(bigDoutput.scale() == scale);
	}

	@Test
	public void digestingAndPopulatingACompoundClassWhichHasOtherContracts() {
		CompoundMockObject cmo = (CompoundMockObject) cd.digest(
				CompoundMockObjectContract.class).make();

		assertNotNull(cmo.getCompoundId());
		assertTrue(StringUtils.isAlphanumeric(cmo.getCompoundId()));

		assertNotNull(cmo.getSigmo());
		assertSigmo(cmo.getSigmo());

		assertNotNull(cmo.getSmo());
		assertSmo(cmo.getSmo());
	}

	private void assertSigmo(SimpleIntegerGenerationMockObject sigmo) {
		assertNotNull(sigmo.getMockNoLimitInteger());
		assertTrue(sigmo.getMockNoLimitInteger() >= 0);

		assertNotNull(sigmo.getMockDefaultLimitInteger());
		assertTrue(sigmo.getMockDefaultLimitInteger() > Integer.MIN_VALUE
				&& sigmo.getMockDefaultLimitInteger() < Integer.MAX_VALUE);

		assertNotNull(sigmo.getMockHighLimitInteger());
		assertNotNull(sigmo.getMockHighLimitInteger() <= 1000000);

		assertNotNull(sigmo.getMockLowLimitInteger());
		assertNotNull(sigmo.getMockLowLimitInteger() >= 1000001);

		assertNotNull(sigmo.getMockBothLimitInteger());
		assertTrue(sigmo.getMockBothLimitInteger() >= 10000
				&& sigmo.getMockDefaultLimitInteger() <= 20000);

		assertNotNull(sigmo.getMockNegativeInteger());
		assertTrue(sigmo.getMockNegativeInteger() <= -1);
	}

	private void assertSmo(SimpleMockObject smo) {
		assertNotNull(smo.getMockUnSizedString());

		assertNotNull(smo.getMockSizedString());
		assertEquals(32, smo.getMockSizedString().length());

		assertNotNull(smo.getMockedSizedStringParam());
		assertEquals(50, smo.getMockedSizedStringParam().length());

		assertNotNull(smo.getMockAlphabeticString());
		assertTrue(StringUtils.isAlpha(smo.getMockAlphabeticString()));

		assertNotNull(smo.getMockAlphabeticSizedString());
		assertTrue(StringUtils.isAlpha(smo.getMockAlphabeticSizedString()));
		assertEquals(40, smo.getMockAlphabeticSizedString().length());

		assertNotNull(smo.getMockAlphanumericString());
		assertTrue(StringUtils.isAlphanumeric(smo.getMockAlphanumericString()));

		assertNotNull(smo.getMockSizedAlphanumericString());
		assertTrue(StringUtils.isAlphanumeric(smo
				.getMockSizedAlphanumericString()));
		assertEquals(40, smo.getMockSizedAlphanumericString().length());

		String[] fromList = { "Mock1", "Mock2", "Mock3" };
		assertNotNull(smo.getMockStringFromList());
		assertTrue(Arrays.binarySearch(fromList, smo.getMockStringFromList()) >= 0);
	}
}
