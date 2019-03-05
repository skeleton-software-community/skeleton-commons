package org.sklsft.commons.rest.security.tokens.verification.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import org.sklsft.commons.crypto.accessors.RsaPublicKeyAccessor;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.rs.BasicRsJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.rs.RsAlgorithms;
import org.sklsft.commons.rest.security.tokens.verification.TokenVerifier;

/**
 *
 * @param <T>
 * 
 * @author Nicolas Thibault
 * 
 */
public class RsJwtVerifier<T extends JsonWebToken<H, B>, H extends BasicRsJwtHeader, B> implements TokenVerifier<T> {

	private RsaPublicKeyAccessor rsaPublicKeyAccessor;
	
	
	
	@Override
	public void verifyToken(T token) {
		RsAlgorithms rsAalgorithm = RsAlgorithms.valueOf(token.getHeader().getAlgorithm());
		if (rsAalgorithm == null) {
			throw new InvalidTokenException("Unsupported algorithm");
		}
		String algorithm = rsAalgorithm.getName();
		boolean valid = false;
		try {
			Signature signature = Signature.getInstance(algorithm);		
			signature.initVerify(rsaPublicKeyAccessor.getPublicKey(token.getHeader().getPublicKeyId()));
			valid = signature.verify(token.getSignature());
		} catch (InvalidKeyException e) {
			throw new InvalidTokenException("Public key not found", e);
		} catch (NoSuchAlgorithmException e) {
			throw new InvalidTokenException("Unsupported algorithm", e);
		} catch (SignatureException e) {
			throw new InvalidTokenException("Invalid signature", e);
		}
		
		if (!valid) {
			throw new InvalidTokenException("Wrong signature");
		}
	}
}
