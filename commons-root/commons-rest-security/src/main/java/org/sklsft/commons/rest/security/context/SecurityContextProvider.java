package org.sklsft.commons.rest.security.context;

/**
 * Responsible for :
 * <li>Providing an application security context given an application token
 * <li>Providing a user security context given a user token
 * <li>Destroying security contexts
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 */
public interface SecurityContextProvider {

	public void provideUserSecurityContext(String userToken);

	public void provideApplicationSecurityContext(String applicationToken);

	public void clearSecurityContext();
}
