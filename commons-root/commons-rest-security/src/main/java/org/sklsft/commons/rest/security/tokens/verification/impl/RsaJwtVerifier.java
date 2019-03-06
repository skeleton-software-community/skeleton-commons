package org.sklsft.commons.rest.security.tokens.verification.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import org.sklsft.commons.crypto.accessors.RsaPublicKeyAccessor;
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
public class RsaJwtVerifier<T extends JsonWebToken<H, B>, H extends BasicRsaJwtHeader, B> implements TokenVerifier<T> {

	private RsaSignatureVerifier rsaSignatureverifier;
	
	
	
	@Override
	public void verifyToken(T token) {
		RsaAlgorithms rsAalgorithm = RsaAlgorithms.valueOf(token.getHeader().getAlgorithm());
		if (rsAalgorithm == null) {
			throw new InvalidTokenException("Unsupported algorithm");
		}
		String algorithm = rsAalgorithm.getName();
		boolean valid = rsaSignatureverifier.checkSignature(algorithm, token.getHeader().getPublicKeyId(), token.getPayload(), token.getSignature());
		
		if (!valid) {
			throw new InvalidTokenException("Wrong signature");
		}
	}
}
