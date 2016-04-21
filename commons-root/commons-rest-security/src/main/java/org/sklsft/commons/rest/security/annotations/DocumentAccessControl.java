package org.sklsft.commons.rest.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.sklsft.commons.rest.security.aspect.DocumentAccessControlAspect;

/**
 * Annotation used to create joinpoints to be used by the aspect {@link DocumentAccessControlAspect}.
 * The annotation is used on rest controller methods.
 * 
 * @author Alexandre Rupp
 *
 */

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentAccessControl {

}
