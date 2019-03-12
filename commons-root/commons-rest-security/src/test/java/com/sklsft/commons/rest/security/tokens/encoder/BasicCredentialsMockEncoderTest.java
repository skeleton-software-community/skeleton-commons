package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

public class BasicCredentialsMockEncoderTest {
	
	private static BasicCredentialsEncoderMock encoder;
	
	@BeforeClass
	public static void init() {
		encoder = new BasicCredentialsEncoderMock();
	}
	
	@Test
	public void testGoodCredentials() {
		BasicCredentials credentials = encoder.decode("sklgen$nicolas.thibault@sklsft.org");
		System.out.println(credentials);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void testBadCredentials() {
		encoder.decode("Test");
	}

}
