package org.sklsft.commons.crypto.encoding;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;


public class AesKeyRandomGenerator {
	
	public static byte[] generateKey(int keySize) throws NoSuchAlgorithmException {

		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keySize, new SecureRandom());

		Key key = keyGen.generateKey();
		return key.getEncoded();
	}	
}
