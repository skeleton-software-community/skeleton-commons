package org.sklsft.commons.rest.security.tokens.verification.impl;

import java.util.Date;

import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;

/**
 *
 * @param <T>
 * 
 * @author Nicolas Thibault
 * 
 */
public class BasicRsaJwtVerifier extends RsaJwtVerifier<BasicRsaJwtHeader, BasicJwtBody> {

	public BasicRsaJwtVerifier(RsaSignatureVerifier rsaSignatureverifier) {
		super(rsaSignatureverifier);
	}

	@Override
	protected void verifyBody(BasicJwtBody body) {
		if (new Date().after(body.getExpiryDate())) {
			throw new InvalidTokenException("Expired token");
		}
	}	
}
