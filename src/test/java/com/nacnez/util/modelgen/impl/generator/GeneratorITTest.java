package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;

public class GeneratorITTest {
	
	Mirror mirror = new Mirror();

	@Test
	public void testGeneratorChain() {
		Generator strGenFront = new BasicStringGenerator();
		Generator strGenBack = new SizedStringGenerator();
		strGenFront.setNext(strGenBack);
		List<Annotation> constraints1 = getConstraints("setMockUnSizedString");
		assertNotNull(strGenFront.generate(constraints1));
		
		List<Annotation> constraints2 = getConstraints("setMockSizedString");
		String strOut = (String)strGenFront.generate(constraints2);
		assertNotNull(strOut);
		assertEquals(32,strOut.length());
	}
	
	private List<Annotation> getConstraints(String methodName) {
		return mirror.on(SimpleMockGenerationContract.class).reflectAll().annotations().atMethod(methodName).withArgs(String.class);
	}


}
