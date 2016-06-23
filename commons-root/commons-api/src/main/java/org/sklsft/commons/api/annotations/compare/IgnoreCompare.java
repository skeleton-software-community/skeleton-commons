package org.sklsft.commons.api.annotations.compare;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Used to indicates to ignore field when comparing.
 * 
 * @author Nicolas Thibault
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface IgnoreCompare {

	
}
