package com.sklsft.commons.rest.security.tokens.verification;

import java.time.Instant;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSignatureVerifier;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtDecoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.BasicRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.RsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.verification.impl.BasicRsaJwtVerifier;
import org.sklsft.commons.rest.security.tokens.verification.impl.RsaJwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;
import com.sklsft.commons.rest.security.crypto.RsaPublicKeyAccessorMock;

public class RsaJwtVerifierTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RsaJwtVerifierTest.class);

	private static final BasicRsaJwtEncoder encoder = new BasicRsaJwtEncoder(new ObjectMapper(), new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	private static final BasicRsaJwtDecoder decoder = new BasicRsaJwtDecoder(new ObjectMapper());
	
	private static final RsaJwtVerifier<RsaJwtHeader, BasicJwtBody> verifier = new BasicRsaJwtVerifier(new RsaSignatureVerifier(new RsaPublicKeyAccessorMock()));
	
	@Test
	public void testGoodSignature() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(null, null);
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String encoded = encoder.encode(jwt);
		
		JsonWebToken<RsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		
		verifier.verifyToken(decoded);
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testBadSignature() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
		String encoded = encoder.encode(jwt);
		encoded = encoded + "A";
		
		JsonWebToken<RsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		
		try {
			verifier.verifyToken(decoded);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
	
	
	@Test(expected=InvalidTokenException.class)
	public void testModifiedBody() throws JsonProcessingException {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.com");
		body.setExpiryDate(Date.from(Instant.now().plusSeconds(3600)));
		
		RsaJwtHeader header = new RsaJwtHeader(RsaAlgorithms.RS256, "test");
		
		BasicRsaJsonWebToken jwt = new BasicRsaJsonWebToken(header, body);
		
		String encoded = encoder.encode(jwt);
		
		String[] parts = encoded.split("\\.");
		
		BasicJwtBody modifiedBody = new BasicJwtBody();
		modifiedBody.setApplication("sklgen");
		modifiedBody.setUser("nicolas.thibault@sklsft.com");
		String wrongPart = Base64.encodeBase64URLSafeString(new ObjectMapper().writeValueAsBytes(modifiedBody));
		
		String fake = parts[0] + "." + wrongPart + "." + parts[2];
		
		JsonWebToken<RsaJwtHeader, BasicJwtBody> decoded = decoder.decode(fake);
		
		try {
			verifier.verifyToken(decoded);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
	}
}
