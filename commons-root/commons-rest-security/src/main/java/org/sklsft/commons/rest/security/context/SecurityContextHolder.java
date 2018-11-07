package org.sklsft.commons.rest.security.context;

import org.sklsft.commons.rest.security.exception.CredentialsConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundCredentialsException;

/**
 * A security context is handled by a ThreadLocal
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public class SecurityContextHolder {

	private static ThreadLocal<Object> allCredentials = new ThreadLocal<>();

	public static void bindCredentials(Object credentials) {
		if (credentials == null) {
			throw new NullPointerException("Cannot bind credentials : provided credentials is null");
		}

		Object currentCredentials = getCredentialsOrNull();
		if (currentCredentials != null) {
			throw new CredentialsConflictException("Credentials has already been bound to the Thread");
		}

		allCredentials.set(credentials);
	}

	

	public static void unbindCredentials() {
		allCredentials.remove();
	}

	public static Object getCredentialsOrNull() {
		return allCredentials.get();
	}


	public static Object getUserCredentials() {
		Object credentials = getCredentialsOrNull();
		if (credentials == null) {
			throw new NoBoundCredentialsException("No credentials bound to Thread");
		}
		return credentials;
	}
}
