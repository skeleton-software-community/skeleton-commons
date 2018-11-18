package org.sklsft.commons.api.annotations.compare;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Used to indicates a comparison or a copy must go into a property and introspect its properties.
 * 
 * @author Nicolas Thibault
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Deep {

	
}
