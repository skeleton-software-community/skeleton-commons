package org.sklsft.commons.crypto.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

import org.sklsft.commons.crypto.accessors.RsaPublicKeyAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsaSignatureVerifier {
	
	private static final Logger logger = LoggerFactory.getLogger(RsaSignatureVerifier.class);
	
	public RsaSignatureVerifier(RsaPublicKeyAccessor rsaPublicKeyAccessor) {
		this.rsaPublicKeyAccessor = rsaPublicKeyAccessor;
	}
	
	private RsaPublicKeyAccessor rsaPublicKeyAccessor;

	public boolean checkSignature(RsaAlgorithms algorithm, String keyId, byte[] data, byte[] signing) {
		
		try {
			Signature signature = Signature.getInstance(algorithm.getFullName());
			signature.initVerify(rsaPublicKeyAccessor.getPublicKey(keyId));
			signature.update(data);
			return signature.verify(signing);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage(), e);
			}
			return false;
		}
	}
}
