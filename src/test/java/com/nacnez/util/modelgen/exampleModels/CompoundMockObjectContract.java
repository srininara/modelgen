package com.nacnez.util.modelgen.exampleModels;

import com.nacnez.util.modelgen.GenerationContract;
import com.nacnez.util.modelgen.impl.contract.Contract;
import com.nacnez.util.modelgen.impl.contract.TargetModel;
import com.nacnez.util.modelgen.impl.generator.rules.Alphanumeric;

@TargetModel(modelClass=CompoundMockObject.class)
public interface CompoundMockObjectContract  extends GenerationContract {

	@Alphanumeric
	void setCompoundId(String compoundId);

	@Contract(contractClass=SimpleMockGenerationContract.class)
	void setSmo(SimpleMockObject smo);

	@Contract(contractClass=SimpleIntegerGenerationMockContract.class)
	void setSigmo(SimpleIntegerGenerationMockObject sigmo);

}