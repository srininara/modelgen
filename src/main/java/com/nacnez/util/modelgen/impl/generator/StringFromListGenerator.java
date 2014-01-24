package com.nacnez.util.modelgen.impl.generator;

import com.google.inject.Inject;
import com.nacnez.util.modelgen.generator.rules.FromList;
import com.nacnez.util.modelgen.impl.generator.model.ConstraintList;
import com.nacnez.util.modelgen.impl.generator.random.RandomIntegerGenerator;

public class StringFromListGenerator extends BaseGenerator implements Generator {

	@Inject
	private RandomIntegerGenerator rig;
	
	@Override
	protected Object doGenerate(ConstraintList constraintList) {
		FromList constraint = (FromList) constraintList.get(FromList.class);
		String[] fromList = constraint.fromList();
		int index = ((Integer)rig.generate(fromList.length-1));
		return fromList[index];
	}

	@Override
	protected boolean applicable(ConstraintList constraintList) {
		return constraintList.contains(FromList.class);
	}

	
}
