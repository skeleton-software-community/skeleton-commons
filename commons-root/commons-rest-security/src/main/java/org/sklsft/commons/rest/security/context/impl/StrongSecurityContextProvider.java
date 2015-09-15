package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
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
public class StrongSecurityContextProvider<T, V> implements SecurityContextProvider {

	private SecurityCredentialsEncoder<T> decodeUserCredentials;
	private SecurityCredentialsValidator<T> validatorUserCredentials;

	private SecurityCredentialsEncoder<V> decodeApplicationCredentials;
	private SecurityCredentialsValidator<V> validatorApplicationCredentials;

	public StrongSecurityContextProvider(SecurityCredentialsEncoder<T> decodeUserCredentials,
			SecurityCredentialsValidator<T> validatorUserCredentials, SecurityCredentialsEncoder<V> decodeApplicationCredentials,
			SecurityCredentialsValidator<V> validatorApplicationCredentials) {
		super();
		this.decodeUserCredentials = decodeUserCredentials;
		this.validatorUserCredentials = validatorUserCredentials;
		this.decodeApplicationCredentials = decodeApplicationCredentials;
		this.validatorApplicationCredentials = validatorApplicationCredentials;

	}

	public void provideUserSecurityContext(String token) {

		T credentials = decodeUserCredentials.decode(token);

		validatorUserCredentials.validateCredentials(credentials);

		SecurityContextHolder.bindUserCredentials(credentials);
	}

	public void provideApplicationSecurityContext(String secretKey) {
		V credentials = decodeApplicationCredentials.decode(secretKey);

		validatorApplicationCredentials.validateCredentials(credentials);

		SecurityContextHolder.bindApplicationCredentials(credentials);

	}

	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
