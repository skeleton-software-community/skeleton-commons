package org.sklsft.commons.mvc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation will be used to make a method handled by the {@link org.sklsft.commons.mvc.loading.PageLoadAspect}
 * 
 * @author Nicolas Thibault
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface PageLoad {
	

}
