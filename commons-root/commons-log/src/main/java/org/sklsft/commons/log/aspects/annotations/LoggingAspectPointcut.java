package org.sklsft.commons.log.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface LoggingAspectPointcut {
	
	/**
	 * will be used to name the transaction type
	 */
	String value() default "";
	
	/**
	 * will be used to check if requestBody will be logged
	 */
	boolean traceRequestBody() default true;
	
	/**
	 * will be used to check if responseBody will be logged
	 */
	boolean traceResponseBody() default true;

}
