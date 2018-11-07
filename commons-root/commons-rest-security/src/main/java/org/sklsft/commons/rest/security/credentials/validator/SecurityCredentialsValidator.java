package org.sklsft.commons.rest.security.credentials.validator;

/**
 * Responsible for credentials validation. Has to be fully implemented since it depends on 
 * <li>the choice of the credentials type
 * <li>the way user data is stored
 * 
 * @author Nicolas Thibault, Abdessalam El Jai, Alexandre Rupp
 *
 * @param <T>
 */
public interface SecurityCredentialsValidator<T> {
	
	void validateCredentials(T securityCredentials, String token);

}
