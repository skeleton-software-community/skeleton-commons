package org.sklsft.commons.rest.security.tokens.encoder.impl;

import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <H>
 * @param <B>
 */
public class PrivateRsaJwtEncoder<H, B> extends JwtEncoder<H, B> {
	
	private RsaSigner rsaSigner;
	private RsaAlgorithms algorithm;
	private String keyId;
	
	
	public PrivateRsaJwtEncoder(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass, RsaSigner rsaSigner, String algorithm, String keyId) {
		super(objectMapper, headerClass, bodyClass);
		this.rsaSigner = rsaSigner;
		this.algorithm = RsaAlgorithms.valueOf(algorithm);
		this.keyId = keyId;
	}
	

	protected byte[] sign(byte[] payload) {
		return rsaSigner.sign(algorithm, keyId, payload);
	}

}
