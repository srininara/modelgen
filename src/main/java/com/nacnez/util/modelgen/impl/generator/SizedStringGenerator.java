package com.nacnez.util.modelgen.impl.generator;

import static com.nacnez.util.modelgen.impl.generator.GeneratorUtil.isConstraintPresent;

import java.lang.annotation.Annotation;
import java.util.List;

import com.nacnez.util.modelgen.impl.generator.rules.Size;

public class SizedStringGenerator implements Generator {

	RandomStringGenerator rsg = new ApacheCommonsRandomStringGeneratorImpl(new JavaUtilRandomIntegerGeneratorImpl()); // DI required
	
	private Generator next;
	
	public Object generate(List<Annotation> constraints) {
		ConstraintList constraintList = convert(constraints);
		if (!constraintList.contains(Size.class)) {
			if (this.next!=null) {
				return this.next.generate(constraints);
			} else {
				return null;
			}
		}
		int index = -1;
		int size = DEFAULT_LENGTH;
		if((index=isConstraintPresent(constraints,Size.class))!=-1) {
			size = ((Size)constraints.get(index)).maxSize();
		}
		return rsg.generate(size);
	}

	public void setNext(Generator next) {
		this.next = next;
	}

	ConstraintList convert(List<Annotation> constraints) {
		return new ConstraintList(constraints);
	}

}
