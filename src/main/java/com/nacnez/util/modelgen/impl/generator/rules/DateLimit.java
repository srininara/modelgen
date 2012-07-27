package com.nacnez.util.modelgen.impl.generator.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateLimit {
	String lowLimit();
	String highLimit();
	String format() default "dd-mm-yyyy";
}
