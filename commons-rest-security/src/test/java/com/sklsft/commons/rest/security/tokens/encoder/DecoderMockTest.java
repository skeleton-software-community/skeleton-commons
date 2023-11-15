package com.sklsft.commons.rest.security.tokens.encoder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sklsft.commons.rest.security.exception.InvalidTokenException;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

public class DecoderMockTest {
	
	private static DecoderMock decoder;
	
	@BeforeClass
	public static void init() {
		decoder = new DecoderMock();
	}
	
	@Test
	public void testGoodContext() {
		SecurityContextMock context = decoder.decode("sklgen$nicolas.thibault@sklsft.org");
		System.out.println(context);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void testBadContext() {
		decoder.decode("Test");
	}

}
