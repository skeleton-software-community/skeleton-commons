package org.sklsft.commons.rest.security.context;

/**
 * Responsible for Providing a security context given a user token
 *
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface SecurityContextProvider {

	public void provideSecurityContext(String token);

}
