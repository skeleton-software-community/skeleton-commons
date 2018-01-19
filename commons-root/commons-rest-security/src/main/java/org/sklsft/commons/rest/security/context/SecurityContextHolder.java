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

	private static ThreadLocal<Object> userCredentials = new ThreadLocal<>();
	private static ThreadLocal<Object> applicationCredentials = new ThreadLocal<>();

	public static void bindUserCredentials(Object credentials) {
		if (credentials == null) {
			throw new NullPointerException("Cannot bind credentials : provided credentials is null");
		}

		Object currentCredentials = getUserCredentialsOrNull();
		if (currentCredentials != null) {
			throw new CredentialsConflictException("Credentials has already been bound to the Thread");
		}

		userCredentials.set(credentials);
	}

	public static void bindApplicationCredentials(Object credentials) {
		if (credentials == null) {
			throw new NullPointerException("Cannot bind credentials : provided credentials is null");
		}

		Object currentCredentials = getApplicationCredentialsOrNull();
		if (currentCredentials != null) {
			throw new CredentialsConflictException("Credentials has already been bound to the Thread");
		}

		applicationCredentials.set(credentials);
	}

	public static void unbindCredentials() {
		userCredentials.remove();
		applicationCredentials.remove();
	}

	public static Object getUserCredentialsOrNull() {
		return userCredentials.get();
	}

	public static Object getApplicationCredentialsOrNull() {
		return applicationCredentials.get();
	}

	public static Object getUserCredentials() {
		Object credentials = getUserCredentialsOrNull();
		if (credentials == null) {
			throw new NoBoundCredentialsException("No credentials bound to Thread");
		}
		return credentials;
	}

	public static Object getApplicationCredentials() {
		Object credentials = getApplicationCredentialsOrNull();
		if (credentials == null) {
			throw new NoBoundCredentialsException("No credentials bound to Thread");
		}
		return credentials;
	}
}
