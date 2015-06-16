package org.sklsft.commons.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;


public class KeyRandomGenerator {
	
	public static byte[] generateKey(String symmetricAlgorithm, int keySize) throws NoSuchAlgorithmException {

		KeyGenerator keyGen = KeyGenerator.getInstance(CryptoUtils.getKeyAlgorithm(symmetricAlgorithm));
		keyGen.init(keySize, new SecureRandom());

		Key key = keyGen.generateKey();
		return key.getEncoded();
	}	
}
