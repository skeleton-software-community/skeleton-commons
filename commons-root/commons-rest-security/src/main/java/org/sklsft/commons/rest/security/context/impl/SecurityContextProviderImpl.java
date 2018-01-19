package org.sklsft.commons.rest.security.context.impl;

import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.SecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.SecurityCredentialsRetriever;

/**
 * Implementation of {@link SecurityContextProvider} based on the use of :
 * <li>a {@link SecurityContextHolder} which is based on {@link ThreadLocal}
 * <li>two {@link SecurityCredentialsRetriever}
 * 
 * @author Nicolas Thibault, Abdessalam El Jai
 */
public class SecurityContextProviderImpl<A, U> implements SecurityContextProvider {

	private SecurityCredentialsRetriever<A> applicationCredentialsRetriever;
	private SecurityCredentialsRetriever<U> userCredentialsRetriever;
	

	public SecurityContextProviderImpl(SecurityCredentialsRetriever<A> applicationCredentialsRetriever,
			SecurityCredentialsRetriever<U> userCredentialsRetriever) {
		super();
		this.applicationCredentialsRetriever = applicationCredentialsRetriever;
		this.userCredentialsRetriever = userCredentialsRetriever;
	}
	
	
	@Override
	public void provideApplicationSecurityContext(String applicationToken) {

		A credentials = applicationCredentialsRetriever.retrieveCredentials(applicationToken);
		SecurityContextHolder.bindApplicationCredentials(credentials);

	}
	
	@Override
	public void provideUserSecurityContext(String userToken) {

		U credentials = userCredentialsRetriever.retrieveCredentials(userToken);
		SecurityContextHolder.bindUserCredentials(credentials);
	}

	@Override
	public void clearSecurityContext() {
		SecurityContextHolder.unbindCredentials();
	}
}
