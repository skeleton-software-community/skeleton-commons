package org.sklsft.commons.rest.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sklsft.commons.rest.security.validation.SecurityLevels;

public @interface SecurityLevel {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface AjaxMethod {

		/**
		 * will be used to get level
		 * 
		 * @return
		 */
		SecurityLevels value() default SecurityLevels.PRIVATE;
	}
}
