package com.nacnez.util.modelgen.impl.generator.random;

import java.math.BigDecimal;

public interface RandomBigDecimalGenerator {
	
	BigDecimal generate(BigDecimal lowLimit, BigDecimal highLimit,int scale);
	
	BigDecimal generate(); 

}
