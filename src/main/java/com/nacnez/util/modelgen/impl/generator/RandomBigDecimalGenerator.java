package com.nacnez.util.modelgen.impl.generator;

import java.math.BigDecimal;

public interface RandomBigDecimalGenerator {
	
	BigDecimal generate(BigDecimal lowLimit, BigDecimal highLimit,int scale);
	
	BigDecimal generate(); 

}
