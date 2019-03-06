package org.sklsft.commons.crypto.accessors;

import java.security.NoSuchAlgorithmException;

import org.sklsft.commons.crypto.accessors.AesKeyAccessor;
import org.sklsft.commons.crypto.encoding.AesKeyRandomGenerator;

public class RandomAesKeyAccessor implements AesKeyAccessor {
	
	private static final byte[] KEY = initKey();
	
	
	@Override
	public byte[] getAesKey() {
		return KEY;
	}

	
	private static byte[] initKey() {
		try {
			return AesKeyRandomGenerator.generateKey(128);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
