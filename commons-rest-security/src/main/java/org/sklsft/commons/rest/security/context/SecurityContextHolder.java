package org.sklsft.commons.rest.security.context;

import org.sklsft.commons.rest.security.exception.ContextConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundContextException;

/**
 * A security context is handled by a ThreadLocal
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class SecurityContextHolder {

	private static ThreadLocal<Object> allContexts = new ThreadLocal<>();

	public static void bindContext(Object context) {
		if (context == null) {
			throw new NullPointerException("Cannot bind context : provided context is null");
		}

		Object currentContext = getContextOrNull();
		if (currentContext != null) {
			throw new ContextConflictException("Context has already been bound to the Thread");
		}

		allContexts.set(context);
	}

	

	public static void unbindContext() {
		allContexts.remove();
	}

	public static Object getContextOrNull() {
		return allContexts.get();
	}


	public static Object getContext() {
		Object credentials = getContextOrNull();
		if (credentials == null) {
			throw new NoBoundContextException("No context bound to Thread");
		}
		return credentials;
	}
}
