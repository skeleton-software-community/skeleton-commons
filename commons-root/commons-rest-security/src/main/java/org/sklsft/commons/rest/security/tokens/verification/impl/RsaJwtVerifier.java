package org.sklsft.commons.rest.security.tokens.verification.impl;

import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 *
 * @param <T>
 * 
 * @author Nicolas Thibault
 * 
 */
public abstract class RsaJwtVerifier<H extends BasicRsaJwtHeader, B> implements TokenVerifier<JsonWebToken<H, B>> {

	private RsaSignatureVerifier rsaSignatureverifier;
	
	
	public RsaJwtVerifier(RsaSignatureVerifier rsaSignatureverifier) {
		super();
		this.rsaSignatureverifier = rsaSignatureverifier;
	}



	@Override
	public void verifyToken(JsonWebToken<H, B> token) {
		RsaAlgorithms rsAalgorithm = RsaAlgorithms.valueOf(token.getHeader().getAlgorithm());
		if (rsAalgorithm == null) {
			throw new InvalidTokenException("Unsupported algorithm");
		}
		boolean valid = rsaSignatureverifier.checkSignature(rsAalgorithm, token.getHeader().getPublicKeyId(), token.getPayload(), token.getSignature());
		
		if (!valid) {
			throw new InvalidTokenException("Wrong signature");
		}
		
		verifyBody(token.getBody());
	}

	protected abstract void verifyBody(B body);
}
