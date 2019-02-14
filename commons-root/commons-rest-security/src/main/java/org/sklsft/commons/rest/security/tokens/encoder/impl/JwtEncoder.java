package org.sklsft.commons.rest.security.tokens.encoder.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;
import org.sklsft.commons.rest.security.tokens.jwt.JwtBody;
import org.sklsft.commons.rest.security.tokens.jwt.JwtHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtEncoder<T extends JsonWebToken<H, B>, H extends JwtHeader, B extends JwtBody> implements TokenEncoder<T> {
	
	private ObjectMapper objectMapper;
	private Class<H> headerClass;
	private Class<B> bodyClass;

	public JwtEncoder(ObjectMapper objectMapper, Class<H> headerClass, Class<B> bodyClass) {
		super();
		this.objectMapper = objectMapper;
		this.headerClass = headerClass;
		this.bodyClass = bodyClass;
	}
	
	

	@Override
	public T decode(String token) {
		String[] parts = token.split(".");
		JsonWebToken<H, B> result = new JsonWebToken<>();
		
		try {
			result.setHeader(objectMapper.readValue(Base64.decodeBase64(parts[0]), headerClass));
			result.setBody(objectMapper.readValue(Base64.decodeBase64(parts[1]), bodyClass));
			result.setSignature(Base64.decodeBase64(parts[2]));
			result.setPayload((parts[0] + "." + parts[1]).getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new InvalidTokenException(e.getMessage(), e);
		}
		
		return null;
	}

	@Override
	public String encode(T token) {
		// TODO Auto-generated method stub
		return null;
	}

}
