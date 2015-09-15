package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.validation.SecurityCredentialsValidator;

/**
 * this class is responsible for creating and destroying a security context
 * given a token this security context will be handled by a ThreadLocal so that
 * it will be accessible in the hole Thread execution's scope
 * 
 * @author Nicolas Thibault
 *
 */
public class WeakSecurityContextProvider<T> implements SecurityContextProvider {

	private SecurityCredentialsEncoder<T> decodeUserCredentials;
	private SecurityCredentialsValidator<T> validatorUserCredentials;

	public WeakSecurityContextProvider(SecurityCredentialsEncoder<T> decodeUserCredentials,
			SecurityCredentialsValidator<T> validatorUserCredentials) {
		super();
		this.decodeUserCredentials = decodeUserCredentials;
		this.validatorUserCredentials = validatorUserCredentials;

	}

	public void provideUserSecurityContext(String token) {

		T credentials = decodeUserCredentials.decode(token);

		validatorUserCredentials.validateCredentials(credentials);

		SecurityContextHolder.bindUserCredentials(credentials);
	}

	public void provideApplicationSecurityContext(String secretKey) {

	}

	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
