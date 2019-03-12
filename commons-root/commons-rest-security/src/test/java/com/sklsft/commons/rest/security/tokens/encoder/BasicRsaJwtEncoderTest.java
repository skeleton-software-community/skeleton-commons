package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.crypto.signature.RsaAlgorithms;
import org.sklsft.commons.crypto.signature.RsaSigner;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PrivateRsaJwtEncoder;
import org.sklsft.commons.rest.security.tokens.encoder.impl.PublicJwtDecoder;
import org.sklsft.commons.rest.security.tokens.jwt.BasicJwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.BasicRsaJwtHeader;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklsft.commons.rest.security.crypto.RsaPrivateKeyAccessorMock;

public class BasicRsaJwtEncoderTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicRsaJwtEncoderTest.class);

	private static final PrivateRsaJwtEncoder<BasicRsaJwtHeader, BasicJwtBody> encoder = new PrivateRsaJwtEncoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class, new RsaSigner(new RsaPrivateKeyAccessorMock()), RsaAlgorithms.RS256.name(), "test");
	
	private static final PublicJwtDecoder<BasicRsaJwtHeader, BasicJwtBody> decoder = new PublicJwtDecoder<>(new ObjectMapper(), BasicRsaJwtHeader.class, BasicJwtBody.class);
	
	@Test
	public void test() {
		
		BasicJwtBody body = new BasicJwtBody();
		body.setApplication("sklgen");
		body.setUser("nicolas.thibault@sklsft.org");
		
		BasicRsaJwtHeader header = new BasicRsaJwtHeader();
		header.setAlgorithm(RsaAlgorithms.RS256.name());
		header.setPublicKeyId("test");
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> jwt = new JsonWebToken<>();
		jwt.setHeader(header);
		jwt.setBody(body);
		
		String encoded = encoder.encode(jwt);
		logger.debug(encoded);
		
		JsonWebToken<BasicRsaJwtHeader, BasicJwtBody> decoded = decoder.decode(encoded);
		logger.debug(decoded.getHeader().getAlgorithm());
		logger.debug(decoded.getHeader().getPublicKeyId());
		logger.debug(decoded.getBody().getApplication());
		logger.debug(decoded.getBody().getUser());
		
		Assert.assertTrue(decoded.getHeader().getAlgorithm().equals(RsaAlgorithms.RS256.name()));
		Assert.assertTrue(decoded.getHeader().getPublicKeyId().equals("test"));
		Assert.assertTrue(decoded.getBody().getApplication().equals("sklgen"));
		Assert.assertTrue(decoded.getBody().getUser().equals("nicolas.thibault@sklsft.org"));
	}
}
