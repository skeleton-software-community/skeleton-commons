package com.sklsft.commons.rest.security.credentials.encoder;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;

import com.sklsft.commons.rest.security.tokens.encoder.BasicCredentialsMockEncoder;

public class UserSecurityCredentialsEncoderMockTest {

	private static BasicCredentialsMockEncoder encoder = new BasicCredentialsMockEncoder();
	
	@Test
	public void testEncode() {
		BasicJwtBody credentials = new BasicJwtBody();
		credentials.setApplication("sklgen");
		credentials.setUser("nicolas.thibault@sklsft.org");
		
		String token = encoder.encode(credentials);
		System.out.println(token);
		
		BasicJwtBody decoded = encoder.decode(token);
		Assert.assertEquals(credentials, decoded);
	}
}
