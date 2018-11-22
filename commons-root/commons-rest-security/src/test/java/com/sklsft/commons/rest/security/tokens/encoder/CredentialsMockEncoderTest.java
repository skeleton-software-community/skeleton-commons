package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.tokens.CredentialsMock;

public class CredentialsMockEncoderTest {
	
	private static CredentialsMockEncoder encoder;
	
	@BeforeClass
	public static void init() {
		encoder = new CredentialsMockEncoder();
	}
	
	@Test
	public void testGoodCredentials() {
		CredentialsMock credentials =encoder.decode("Nicolas$Thibault");
		System.out.println(credentials);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void testBadCredentials() {
		encoder.decode("Test");
	}

}
