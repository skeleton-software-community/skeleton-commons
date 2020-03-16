package org.sklsft.commons.jms.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface JmsChannel {

	/**
	 * will be used to name a JMS Channel
	 * 
	 * @return
	 */
	String value();
}
