package org.sklsft.commons.crypto.encoding;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.encoding.AesJsonObjectEncoder;
import org.sklsft.commons.crypto.miscellaneous.TestObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AesObjectEncoderTest {
	
	
	private static AesJsonObjectEncoder objectEncoder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		objectEncoder = new AesJsonObjectEncoder(new ObjectMapper(), new RandomAesKeyAccessor());
	}

	@Test
	public void testObjectEncoder() throws NoSuchAlgorithmException {
		
		TestObject plainObject = new TestObject("test", new Date());
		System.out.println(plainObject);
		
		String cryptedText = objectEncoder.encode(plainObject);
		System.out.println(cryptedText);
		
		TestObject decryptedObject = objectEncoder.decode(cryptedText, TestObject.class);
		System.out.println(decryptedObject);
		
		Assert.assertEquals(decryptedObject,plainObject);
		
	}
		
}

