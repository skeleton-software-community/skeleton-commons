package org.sklsft.commons.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.sklsft.commons.crypto.exception.CryptingException;

public class StringEncoder {
	
	public static String encode(String plainText, String symmetricAlgorithm, byte[] key) {
		SecretKeySpec secretKey = new SecretKeySpec(key,CryptoUtils.getKeyAlgorithm(symmetricAlgorithm));
		try {
			Cipher cipher = Cipher.getInstance(symmetricAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encodeBase64URLSafeString(cipher.doFinal(plainText.getBytes()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new CryptingException("Failed to encode " + plainText, e);
		}
	}
	
	public static String decode(String cryptedText, String symmetricAlgorithm, byte[] key) {
		SecretKeySpec secretKey = new SecretKeySpec(key,CryptoUtils.getKeyAlgorithm(symmetricAlgorithm));
		try {
			Cipher cipher = Cipher.getInstance(symmetricAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String((cipher.doFinal(Base64.decodeBase64(cryptedText))));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new CryptingException("Failed to decode " + cryptedText, e);
		}
	}

}
