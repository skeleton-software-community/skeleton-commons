package org.sklsft.commons.mvc.ajax;


/**
 * This interface must be used in combination to the {@link AjaxMethodExecutor}<br>
 * It simply templatize the proceeding of a ajax method call :
 * <li> a service execution that can produce a result
 * <li> a redirection that can depend on the result of the execution
 * 
 * @author Nicolas Thibault
 *
 */
public interface AjaxMethodTemplate {
	
	Object execute() throws Throwable;
	
	void redirectOnComplete(Object result);

}
