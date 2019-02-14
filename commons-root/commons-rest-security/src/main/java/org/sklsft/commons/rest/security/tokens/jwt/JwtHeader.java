package org.sklsft.commons.rest.security.tokens.jwt;

import java.io.Serializable;

public interface JwtHeader extends Serializable {

	String getAlgorithm();
	
	String getPublicKeyId();

}
