package org.sklsft.commons.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.miscellaneous.TestObject;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

public class ObjectEncoderTest {
	
	private static final String SYMETRIC_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	private static ObjectEncoder objectEncoder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		objectEncoder = new ObjectEncoder(new JsonSerializer(new ObjectMapper()));
	}

	@Test
	public void testObjectEncoder() throws NoSuchAlgorithmException {
		
		byte[] key = KeyRandomGenerator.generateKey(SYMETRIC_ALGORITHM, 128);
		System.out.println(Base64.encodeBase64URLSafeString(key));
		
		Date currentDate = new Date();
		
		TestObject plainObject = new TestObject("test", currentDate);
		System.out.println(plainObject);
		
		String cryptedText = objectEncoder.encode(plainObject, SYMETRIC_ALGORITHM, key);
		System.out.println(cryptedText);
		
		TestObject decryptedObject = objectEncoder.decode(cryptedText, TestObject.class, SYMETRIC_ALGORITHM, key);
		System.out.println(decryptedObject);
		
		Assert.assertEquals(decryptedObject,plainObject);
		
	}	
		
}

