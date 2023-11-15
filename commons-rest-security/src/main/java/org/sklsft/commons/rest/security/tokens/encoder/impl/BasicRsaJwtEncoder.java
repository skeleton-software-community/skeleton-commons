package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 */
public class BasicRsaJwtEncoder extends JwtEncoder<BasicRsaJsonWebToken> {
	
	private RsaSigner rsaSigner;
	private RsaAlgorithms algorithm;
	private String keyId;
	
	
	public BasicRsaJwtEncoder(ObjectMapper objectMapper, RsaSigner rsaSigner, String algorithm, String keyId) {
		super(objectMapper);
		this.rsaSigner = rsaSigner;
		this.algorithm = RsaAlgorithms.valueOf(algorithm);
		this.keyId = keyId;
	}
	

	protected byte[] sign(byte[] payload) {
		return rsaSigner.sign(algorithm, keyId, payload);
	}

}
