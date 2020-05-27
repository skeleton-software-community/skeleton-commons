package org.sklsft.commons.soap.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface SoapOneWaySubscriber {

	/**
	 * will be used to name a SOAP JMS Channel
	 * 
	 * @author Nicolas Thibault
	 */
	String value();
}
