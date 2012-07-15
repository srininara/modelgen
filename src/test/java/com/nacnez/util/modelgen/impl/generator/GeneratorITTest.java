package com.nacnez.util.modelgen.impl.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nacnez.util.modelgen.exampleModels.SimpleMockGenerationContract;
import com.nacnez.util.modelgen.factory.ModelGenModule;

public class GeneratorITTest {
	
	Mirror mirror = new Mirror();

	@Test
	public void testGeneratorChain() {
		Injector injector = Guice.createInjector(new ModelGenModule());
		Generator strGenFront = injector.getInstance(BasicStringGenerator.class);
		Generator strGenBack = new SizedStringDecorator((StringGenerator)strGenFront);
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
