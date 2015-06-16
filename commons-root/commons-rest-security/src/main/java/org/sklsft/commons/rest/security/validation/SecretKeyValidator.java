package org.sklsft.commons.rest.security.validation;

import org.sklsft.commons.rest.security.aspect.AccessControlAspect;

/**
 * The implementation of this interface should only check the secretKey provided by the {@link AccessControlAspect} is valid
 * 
 * @author Nicolas Thibault
 *
 */
public interface SecretKeyValidator {
	
	void validateSecretKey(String secretKey);

}
