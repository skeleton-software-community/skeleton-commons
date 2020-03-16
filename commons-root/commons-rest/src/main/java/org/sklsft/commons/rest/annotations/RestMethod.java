package org.sklsft.commons.rest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface RestMethod {
	
	/**
	 * will be used to name a REST method
	 * 
	 * @return
	 */
	String value();

}
