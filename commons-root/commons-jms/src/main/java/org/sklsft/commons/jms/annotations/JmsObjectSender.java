package org.sklsft.commons.jms.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * will be used to intercept a JMS Object sender
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface JmsObjectSender {

	/**
	 * will be used to name the interface
	 */
	String destination();
}
