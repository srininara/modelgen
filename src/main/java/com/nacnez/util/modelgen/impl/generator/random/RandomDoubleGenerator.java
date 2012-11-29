package com.nacnez.util.modelgen.impl.generator.random;


public interface RandomDoubleGenerator {
	
	Double generate(Double lowLimit, Double highLimit);
	
	Double generate(); 

}
