package org.sklsft.commons.crypto;

import java.security.NoSuchAlgorithmException;

public class RandomKeyEncryptionParametersAccessor implements EncryptionParametersAccessor {

	private static final String SYMETRIC_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	private static final byte[] KEY = initKey();
	
	
	@Override
	public byte[] getTokenEncryptionKey() {
		return KEY;
	}
	

	@Override
	public String getTokenEncryptionSymmetricAlgorithm() {
		return SYMETRIC_ALGORITHM;
	}
	
	
	private static byte[] initKey() {
		try {
			return KeyRandomGenerator.generateKey(SYMETRIC_ALGORITHM, 128);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
