package org.sklsft.commons.rest.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sklsft.commons.rest.security.access.AccessControlType;
import org.sklsft.commons.rest.security.aspect.AccessControlAspect;
import org.sklsft.commons.rest.security.tokens.extraction.TokenExtractionMode;


/**
 * Used to indicates a rest controller method :
 * <li>which {@link AccessControlType} must be considered by the {@link AccessControlAspect}
 * <li>which {@link TokenExtractionMode} to be used to get the tokens
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface AccessControl {

	AccessControlType value() default AccessControlType.PRIVATE;
	TokenExtractionMode tokenExtractionMode() default TokenExtractionMode.HEADER;
}
