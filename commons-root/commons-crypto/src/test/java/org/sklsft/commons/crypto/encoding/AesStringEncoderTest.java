package org.sklsft.commons.crypto.encoding;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.accessors.RandomAesKeyAccessor;

public class AesStringEncoderTest {
	
	private static AesStringEncoder encoder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		encoder = new AesStringEncoder(new RandomAesKeyAccessor());
	}
		
	@Test
	public void testStringEncoder() throws NoSuchAlgorithmException {
		
		String plainText = "test";
		System.out.println(plainText);
		String cryptedText = encoder.encode(plainText);
		System.out.println(cryptedText);
		
		String decryptedText = encoder.decode(cryptedText);
		System.out.println(decryptedText);
		
		Assert.assertEquals(decryptedText,plainText);
	}
	

}
