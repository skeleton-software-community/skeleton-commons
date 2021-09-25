package com.sklsft.commons.rest.security.context;

import java.time.Instant;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.impl.FromBasicRsaJwtSecurityContextProvider;
import org.sklsft.commons.rest.security.context.impl.FromJwtSecurityContextProvider;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PrivateRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.credentials.validator.BasicCredentialsValidatorMock;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;
import com.sklsft.commons.rest.security.crypto.RsaPublicKeyAccessorMock;
import com.sklsft.commons.rest.security.tokens.verification.RsaJwtVerifierTest;

public class FromJwtSecurityContextProviderTest {

	private static final Logger logger = LoggerFactory.getLogger(RsaJwtVerifierTest.class);

	private static final PrivateRsaJwtEncoder<BasicRsaJwtHeader, BasicJwtBody> encoder = new PrivateRsaJwtEncoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");

	private static final FromJwtSecurityContextProvider<BasicRsaJwtHeader, BasicJwtBody, BasicCredentials> provider;
	
	static {
		provider = new FromBasicRsaJwtSecurityContextProvider(new ObjectMapper(), new RsaPublicKeyAccessorMock(), new BasicCredentialsValidatorMock());
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	@Test
	public void testGoodToken() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>(header, body);
		
		String token = encoder.encode(jwt);
		
		provider.provideSecurityContext(token);
		
		BasicCredentials userCredentials = (BasicCredentials) SecurityContextHolder.getCredentials();
		Assert.assertTrue(userCredentials.getUser().equals("nicolas.thibault@sklsft.org") && userCredentials.getApplication().equals("sklgen"));
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testInvalidCredentials() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>(header, body);
		
		String token = encoder.encode(jwt);
		
		try {
			provider.provideSecurityContext(token);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testModifiedBody() throws JsonProcessingException {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>(header, body);
		
		String token = encoder.encode(jwt);
		
		String[] parts = token.split("\\.");
		
		BasicJwtBody modifiedBody = new BasicJwtBody();
		modifiedBody.setApplication("sklgen");
		modifiedBody.setUser("nicolas.thibault@sklsft.com");
		String wrongPart = Base64.encodeBase64URLSafeString(new ObjectMapper().writeValueAsBytes(modifiedBody));
		
		String fake = parts[0] + "." + wrongPart + "." + parts[2];
		
		try {
			provider.provideSecurityContext(fake);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
}
