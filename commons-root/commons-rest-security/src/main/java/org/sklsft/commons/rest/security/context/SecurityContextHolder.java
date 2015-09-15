package org.sklsft.commons.rest.security.context;

import org.sklsft.commons.rest.security.exception.CredentialsConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundCredentialsException;

/**
 * A security context is handled by a ThreadLocal
 * 
 * @author Nicolas Thibault
 *
 */
public class SecurityContextHolder {

	private static ThreadLocal<Object> usersCredentials = new ThreadLocal<>();
	private static ThreadLocal<Object> applicationCredentials = new ThreadLocal<>();

	public static void bindUserCredentials(Object credentials) {
		if (credentials == null) {
			throw new NullPointerException("Cannot bind credentials : provided credentials is null");
		}

		Object currentCredentials = getUserCredentials();
		if (currentCredentials != null) {
			throw new CredentialsConflictException("Credentials has already been bound to the Thread");
		}

		usersCredentials.set(credentials);
	}

	public static void bindApplicationCredentials(Object credentials) {
		if (credentials == null) {
			throw new NullPointerException("Cannot bind credentials : provided credentials is null");
		}

		Object currentCredentials = getApplicationCredentials();
		if (currentCredentials != null) {
			throw new CredentialsConflictException("Credentials has already been bound to the Thread");
		}

		applicationCredentials.set(credentials);
	}

	public static void unbindCredentials() {
		usersCredentials.remove();
		applicationCredentials.remove();
	}

	public static Object getUserCredentials() {
		return usersCredentials.get();
	}

	public static Object getApplicationCredentials() {
		return applicationCredentials.get();
	}

	public static Object getCurrentUserCredentials() {
		Object credentials = getUserCredentials();
		if (credentials == null) {
			throw new NoBoundCredentialsException("No credentials bound to Thread");
		}
		return credentials;
	}

	public static Object getCurrentApplicationCredentials() {
		Object credentials = getApplicationCredentials();
		if (credentials == null) {
			throw new NoBoundCredentialsException("No credentials bound to Thread");
		}
		return credentials;
	}
}
