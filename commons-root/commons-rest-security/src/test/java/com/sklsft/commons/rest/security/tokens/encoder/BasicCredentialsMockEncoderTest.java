package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.jwt.BasicCredentials;

public class BasicCredentialsMockEncoderTest {
	
	private static BasicCredentialsMockEncoder encoder;
	
	@BeforeClass
	public static void init() {
		encoder = new BasicCredentialsMockEncoder();
	}
	
	@Test
	public void testGoodCredentials() {
		BasicCredentials credentials =encoder.decode("Nicolas$Thibault");
		System.out.println(credentials);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void testBadCredentials() {
		encoder.decode("Test");
	}

}
