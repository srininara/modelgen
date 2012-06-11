package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class SizedStringGenerator extends StringGenerator implements Generator {

	RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(
			new JavaUtilRandomIntegerGeneratorImpl()); // DI required

	ConstraintList convert(List<Annotation> constraints) {
		return new ConstraintList(constraints);
	}

	@Override
	protected Object doGenerate(List<Annotation> constraints) {
		ConstraintList constraintList = convert(constraints);
		int size = DEFAULT_LENGTH;
		size = ((Size) constraintList.get(Size.class)).maxSize();
		return rsg.generate(size);
	}

	@Override
	protected boolean applicable(List<Annotation> constraints) {
		ConstraintList constraintList = convert(constraints);
		return constraintList.contains(Size.class);

	}

}
