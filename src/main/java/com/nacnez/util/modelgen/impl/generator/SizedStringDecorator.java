package com.nacnez.util.modelgen.impl.generator;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class SizedStringDecorator extends StringGenerator implements Generator {

	StringGenerator stringGenerator;

	public SizedStringDecorator(StringGenerator stringGenerator) {
		this.stringGenerator = stringGenerator;
	}

	@Override
	protected Object doGenerate(ConstraintList constraintList, int size) {
		return stringGenerator.doGenerate(constraintList,size);
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(Size.class) && stringGenerator.applicable(constraintList);
	}

	@Override
	protected int getLength(ConstraintList constraintList) {
		return ((Size) constraintList.get(Size.class)).maxSize();
	}

}
