package org.sklsft.commons.rest.security.context;

/**
 * Responsible for :
 * <li>Providing a security context given a user token
 * <li>Destroying security contexts
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface SecurityContextProvider {

	public void provideSecurityContext(String token);

}
