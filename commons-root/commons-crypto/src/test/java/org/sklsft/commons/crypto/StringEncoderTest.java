package org.sklsft.commons.crypto;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

public class StringEncoderTest {
		
	private static final String SYMETRIC_ALGORITHM = "AES/ECB/PKCS5Padding";

	@Test
	public void testStringEncoder() throws NoSuchAlgorithmException {
		
		byte[] key = KeyRandomGenerator.generateKey(SYMETRIC_ALGORITHM, 128);
		System.out.println(Base64.encodeBase64URLSafeString(key));
		
		String plainText = "test";
		String cryptedText = StringEncoder.encode(plainText, SYMETRIC_ALGORITHM, key);
		System.out.println(cryptedText);
		
		String decryptedText = StringEncoder.decode(cryptedText, SYMETRIC_ALGORITHM, key);
		System.out.println(decryptedText);
		
		Assert.assertEquals(decryptedText,plainText);
	}
	

}
