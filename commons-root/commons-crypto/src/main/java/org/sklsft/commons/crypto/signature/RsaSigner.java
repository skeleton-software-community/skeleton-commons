package org.sklsft.commons.crypto.signature;

import java.security.PrivateKey;
import java.security.Signature;

import org.sklsft.commons.crypto.accessors.RsaPrivateKeyAccessor;
import org.sklsft.commons.crypto.exception.SignatureException;

public class RsaSigner {
	
	public RsaSigner(RsaPrivateKeyAccessor rsaPrivateKeyAccessor, String keyId, String algorithm) {
		this.privateKey = rsaPrivateKeyAccessor.getPrivateKey(keyId);
		this.algorithm = algorithm;
	}
	
	private PrivateKey privateKey;
	private String algorithm;
	

	public byte[] sign(byte[] data) {
		try {
			Signature signature = Signature.getInstance(algorithm);
	        signature.initSign(privateKey);
	        signature.update(data);
	        return signature.sign();
		} catch (Exception e) {
			throw new SignatureException("failed to sign", e);
		}
	}
}
