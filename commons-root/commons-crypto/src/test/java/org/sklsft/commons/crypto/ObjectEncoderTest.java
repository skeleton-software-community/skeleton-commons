package org.sklsft.commons.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.crypto.miscellaneous.TestObject;
import org.sklsft.commons.crypto.serialization.JsonSerializer;

public class ObjectEncoderTest {
	
	
	
	private static ObjectEncoder objectEncoder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		objectEncoder = new ObjectEncoder(new JsonSerializer(new ObjectMapper()), new RandomKeyEncryptionParametersAccessor());
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

