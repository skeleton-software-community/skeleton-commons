package org.sklsft.commons.rest.security.tokens.jwt;

import java.io.Serializable;

import org.sklsft.commons.crypto.signature.RsaAlgorithms;

public class RsaJwtHeader extends JwtHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * constructor
	 */
	public RsaJwtHeader() {
		super();
		setType("JWT");
	}
	
	public RsaJwtHeader(RsaAlgorithms alg, String publicKeyId) {
		super();
		setAlgorithm(alg.name());
		setType("JWT");
		setPublicKeyId(publicKeyId);
	}
}
