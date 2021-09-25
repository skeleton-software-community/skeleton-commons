package org.sklsft.commons.rest.security.tokens.encoder.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;
import org.sklsft.commons.rest.security.exception.TokenEncodingException;
import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;
import org.sklsft.commons.rest.security.tokens.jwt.JsonWebToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <H>
 * @param <B>
 */
public abstract class JwtEncoder<H, B> implements TokenEncoder<JsonWebToken<H, B>> {
	
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
	public JsonWebToken<H, B> decode(String token) {
		String[] parts = token.split("\\.");
		JsonWebToken<H, B> result = new JsonWebToken<>();
		
		try {
			result.setHeader(objectMapper.readValue(Base64.decodeBase64(parts[0]), headerClass));
			result.setBody(objectMapper.readValue(Base64.decodeBase64(parts[1]), bodyClass));
			result.setSignature(Base64.decodeBase64(parts[2]));
			result.setPayload((parts[0] + "." + parts[1]).getBytes(StandardCharsets.UTF_8));
			
			return result;
		} catch (IOException e) {
			throw new InvalidTokenException(e.getMessage(), e);
		}
	}

	@Override
	public String encode(JsonWebToken<H, B> token) {
		String result = "";
		String headerPart = "";
		String bodyPart = "";
		byte[] payload = null;
		String signaturePart = "";
		
		try {
			headerPart += Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(token.getHeader()));
			bodyPart += Base64.encodeBase64URLSafeString(objectMapper.writeValueAsBytes(token.getBody()));
			result = headerPart + "." + bodyPart;
			payload = result.getBytes(StandardCharsets.UTF_8);
			signaturePart = Base64.encodeBase64URLSafeString(sign(payload));
			result = result + "." + signaturePart;
			
		} catch (JsonProcessingException e) {
			throw new TokenEncodingException("Failed to encode token", e);
		}
		
		return result;
	}



	protected abstract byte[] sign(byte[] payload);

}
