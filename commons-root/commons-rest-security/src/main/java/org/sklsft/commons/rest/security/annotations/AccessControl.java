package org.sklsft.commons.rest.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.sklsft.commons.rest.security.aspect.AccessControlAspect;

import org.sklsft.commons.rest.security.validation.AccessControlType;


/**
 * Used to indicates a rest controller method which {@link AccessControlType} must be considered by the {@link AccessControlAspect}
 * 
 * @author Nicolas Thibault
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface AccessControl {

	AccessControlType value() default AccessControlType.PRIVATE;

}
