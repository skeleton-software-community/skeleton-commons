package com.sklsft.commons.rest.security.tokens.encoder;

import org.sklsft.commons.rest.security.tokens.encoder.TokenEncoder;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

public class EncoderMock implements TokenEncoder<SecurityContextMock> {

	@Override
	public String encode(SecurityContextMock context) {
		String result = "";
		result += context.getApplication();
		result += "$";
		result += context.getUser();
		
		return result;
	}
}
