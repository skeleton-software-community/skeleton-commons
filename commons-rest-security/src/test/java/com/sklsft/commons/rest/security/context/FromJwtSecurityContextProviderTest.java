package com.sklsft.commons.rest.security.context;

import java.time.Instant;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.context.impl.FromBasicRsaJwtSecurityContextProvider;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtDecoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.RsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.verification.impl.BasicRsaJwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.credentials.validator.BasicJwtBodyValidatorMock;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;
import com.sklsft.commons.rest.security.crypto.RsaPublicKeyAccessorMock;
import com.sklsft.commons.rest.security.tokens.verification.RsaJwtVerifierTest;

public class FromJwtSecurityContextProviderTest {

	private static final Logger logger = LoggerFactory.getLogger(RsaJwtVerifierTest.class);

	private static final BasicRsaJwtEncoder encoder = new BasicRsaJwtEncoder(new ObjectMapper(), new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");

	private static final FromBasicRsaJwtSecurityContextProvider provider;
	
	static {
		provider = new FromBasicRsaJwtSecurityContextProvider(new BasicRsaJwtDecoder(new ObjectMapper()), new BasicRsaJwtVerifier(new RsaSignatureVerifier(new RsaPublicKeyAccessorMock())), new BasicJwtBodyValidatorMock());
	}
	
	@After
	public void clear() {
		SecurityContextHolder.unbindContext();
	}
	
	@Test
	public void testGoodToken() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
		String token = encoder.encode(jwt);
		
		provider.provideSecurityContext(token);
		
		BasicJwtBody context = (BasicJwtBody) SecurityContextHolder.getContext();
		Assert.assertTrue(context.getUser().equals("nicolas.thibault@sklsft.org") && context.getApplication().equals("sklgen"));
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testInvalidCredentials() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
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
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
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
