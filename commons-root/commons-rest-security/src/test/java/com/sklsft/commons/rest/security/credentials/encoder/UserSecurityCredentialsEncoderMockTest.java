package com.sklsft.commons.rest.security.credentials.encoder;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.tokens.jwt.BasicCredentials;

import com.sklsft.commons.rest.security.tokens.encoder.BasicCredentialsMockEncoder;

public class UserSecurityCredentialsEncoderMockTest {

	private static BasicCredentialsMockEncoder encoder = new BasicCredentialsMockEncoder();
	
	@Test
	public void testEncode() {
		BasicCredentials credentials = new BasicCredentials();
		credentials.setApplication("sklgen");
		credentials.setUser("nicolas.thibault@sklsft.org");
		
		String token = encoder.encode(credentials);
		System.out.println(token);
		
		BasicCredentials decoded = encoder.decode(token);
		Assert.assertEquals(credentials, decoded);
	}
}
