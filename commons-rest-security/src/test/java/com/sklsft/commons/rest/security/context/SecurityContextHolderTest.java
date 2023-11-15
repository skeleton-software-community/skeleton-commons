package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.exception.ContextConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundContextException;

import com.sklsft.commons.rest.security.tokens.SecurityContextMock;

public class SecurityContextHolderTest {
	
	@After
	public void clear() {
		SecurityContextHolder.unbindContext();
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testBindNullCredentials() {
		SecurityContextHolder.bindContext(null);
	}
	
	
	@Test
	public void testBindCredentials() {
		SecurityContextMock context = new SecurityContextMock();
		SecurityContextHolder.bindContext(context);
		Assert.assertNotNull(SecurityContextHolder.getContextOrNull());
	}
	
	
	@Test(expected=ContextConflictException.class)
	public void testBindUserCredentialsConflict() {
		SecurityContextMock context = new SecurityContextMock();
		SecurityContextHolder.bindContext(context);
		SecurityContextHolder.bindContext(context);
	}
	
	
	@Test(expected=NoBoundContextException.class)
	public void testGetNullUserCredentials() {
		SecurityContextHolder.getContext();
	}
}
