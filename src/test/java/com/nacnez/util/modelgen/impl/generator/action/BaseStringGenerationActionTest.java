package com.nacnez.util.modelgen.impl.generator.action;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BaseStringGenerationActionTest {

	BasicStringGeneration bsgCut;
	
	@Before
	public void setup() {
		bsgCut = new BasicStringGeneration();
	}
	
	@Test
	public void testBasicStringGeneration() {
		String str = (String) bsgCut.generate();
		assertNotNull(str);
		assertTrue(str.length()>0);

		String str2 = (String) bsgCut.generate();
		assertNotNull(str2);
		assertTrue(str2.length()>0);
		assertTrue(!str.equals(str2));

		String str3 = (String) bsgCut.generate();
		assertNotNull(str3);
		assertTrue(str3.length()>0);
		assertTrue(!str.equals(str3));
		assertTrue(!str2.equals(str3));
	}

}
