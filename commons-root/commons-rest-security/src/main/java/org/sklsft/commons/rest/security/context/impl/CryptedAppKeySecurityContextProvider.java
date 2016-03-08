package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.validation.SecurityCredentialsValidator;

/**
 * This class provide security context if both applicationkey and token are
 * required for authentification, famous use case is websites trying to limit
 * them api by throttling via by applicationkey, the credentials of
 * applicationkey will be saved in {@link SecurityContextHolder} as for
 * credentials of token
 * 
 * @author AELJ
 *
 * @param <T>
 *            Credentials of Token
 * @param <V>
 *            Credentials of Application key
 */
public class CryptedAppKeySecurityContextProvider<T, V> implements SecurityContextProvider {

	private SecurityCredentialsEncoder<T> userCredentialsEncoder;
	private SecurityCredentialsValidator<T> userCredentialsValidator;

	private SecurityCredentialsEncoder<V> applicationCredentialsEncoder;
	private SecurityCredentialsValidator<V> applicationCredentialsValidator;

	public CryptedAppKeySecurityContextProvider(SecurityCredentialsEncoder<T> userCredentialsEncoder,
			SecurityCredentialsValidator<T> userCredentialsValidator, SecurityCredentialsEncoder<V> applicationCredentialsEncoder,
			SecurityCredentialsValidator<V> applicationCredentialsValidator) {
		super();
		this.userCredentialsEncoder = userCredentialsEncoder;
		this.userCredentialsValidator = userCredentialsValidator;
		this.applicationCredentialsEncoder = applicationCredentialsEncoder;
		this.applicationCredentialsValidator = applicationCredentialsValidator;

	}

	public void provideUserSecurityContext(String token) {

		T credentials = userCredentialsEncoder.decode(token);

		userCredentialsValidator.validateCredentials(credentials);

		SecurityContextHolder.bindUserCredentials(credentials);
	}

	public void provideApplicationSecurityContext(String secretKey) {
		V credentials = applicationCredentialsEncoder.decode(secretKey);

		applicationCredentialsValidator.validateCredentials(credentials);

		SecurityContextHolder.bindApplicationCredentials(credentials);

	}

	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
