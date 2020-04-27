package org.sklsft.commons.crypto.encoding;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.sklsft.commons.crypto.accessors.AesKeyAccessor;
import org.sklsft.commons.crypto.exception.CryptingException;

public class AesStringEncoder implements StringEncoder {
	
	private AesKeyAccessor keyAccessor;
	
	public AesStringEncoder(AesKeyAccessor keyAccessor) {
		this.keyAccessor = keyAccessor;
	}
	
	@Override
	public String encode(String plainText) {
		SecretKeySpec secretKey = new SecretKeySpec(getKey(), "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.encodeBase64URLSafeString(cipher.doFinal(plainText.getBytes()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new CryptingException("Failed to encode " + plainText, e);
		}
	}
	
	@Override
	public String decode(String cryptedText) {
		SecretKeySpec secretKey = new SecretKeySpec(getKey(), "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String((cipher.doFinal(Base64.decodeBase64(cryptedText))));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new CryptingException("Failed to decode " + cryptedText, e);
		}
	}
	
	private byte[] getKey() {
		return keyAccessor.getAesKey();
	}

}
