package org.sklsft.commons.rest.security.tokens.encoder.impl;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
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
public abstract class JwtEncoder<T extends JsonWebToken> implements TokenEncoder<T> {
	
	private ObjectMapper objectMapper;
	

	public JwtEncoder(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}
	
	

	@Override
	public String encode(T token) {
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
