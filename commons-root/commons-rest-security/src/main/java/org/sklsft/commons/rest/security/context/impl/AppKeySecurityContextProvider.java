package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.ApplicationCredentialsRetriever;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.context.SecurityCredentialsEncoder;
import org.sklsft.commons.rest.security.validation.SecurityCredentialsValidator;

/**
 * This class provide security context if both applicationkey and token are
 * required for authentication, famous use case is websites trying to limit
 * their api by throttling via by applicationkey, the credentials of
 * applicationkey will be saved in {@link SecurityContextHolder} as for
 * credentials of token
 * 
 * the main difference between this class and
 * {@link CryptedAppKeySecurityContextProvider} is that the applicationkey is
 * considered here as plain text not token encrypting data by providing an
 * implemntation of {@link ApplicationCredentialsRetriever} you are free to
 * handle this key as you want
 * 
 * @author Abdessalam ELJAI
 *
 * @param <T>
 *            Credentials of Token
 * @param <V>
 *            Credentials of Application key
 */
public class AppKeySecurityContextProvider<T, V> implements SecurityContextProvider {

	private SecurityCredentialsEncoder<T> userCredentialsEncoder;
	private SecurityCredentialsValidator<T> userCredentialsValidator;

	private ApplicationCredentialsRetriever<V> applicationCredentialsRetriever;

	public AppKeySecurityContextProvider(SecurityCredentialsEncoder<T> userCredentialsEncoder,
			SecurityCredentialsValidator<T> userCredentialsValidator, ApplicationCredentialsRetriever<V> applicationCredentialsRetriever) {
		super();
		this.userCredentialsEncoder = userCredentialsEncoder;
		this.userCredentialsValidator = userCredentialsValidator;
		this.applicationCredentialsRetriever = applicationCredentialsRetriever;
	}

	public void provideUserSecurityContext(String token) {

		T credentials = userCredentialsEncoder.decode(token);

		userCredentialsValidator.validateCredentials(credentials);

		SecurityContextHolder.bindUserCredentials(credentials);
	}

	public void provideApplicationSecurityContext(String secretKey) {

		V credentials = applicationCredentialsRetriever.retrieveApplicationCredentials(secretKey);

		SecurityContextHolder.bindApplicationCredentials(credentials);

	}

	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
