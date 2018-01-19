package com.sklsft.commons.rest.security.tokens;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.tokens.TokenExtractor;

public class TokenExtractorMockTest {

	private TokenExtractor tokenExtractor = new TokenExtractorMock();
	
	@Test
	public void extractUserToken() {
		String userToken = tokenExtractor.extractToken("src/test/resources/tokens/userToken.txt");
		System.out.println(userToken);
		Assert.assertNotNull(userToken);
		
		String appToken = tokenExtractor.extractToken("src/test/resources/tokens/applicationToken.txt");
		System.out.println(appToken);
		Assert.assertNotNull(appToken);
	}
}
