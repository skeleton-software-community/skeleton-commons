package org.sklsft.commons.soap.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface SoapTwoWaysConsumer {

	/**
	 * will be used to name a SOAP service provider
	 * 
	 * @author Nicolas Thibault
	 */
	String value();
}
