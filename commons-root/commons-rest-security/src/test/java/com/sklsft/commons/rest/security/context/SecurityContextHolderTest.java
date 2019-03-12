package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.credentials.BasicCredentials;
import org.sklsft.commons.rest.security.exception.CredentialsConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundCredentialsException;

public class SecurityContextHolderTest {
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testBindNullCredentials() {
		SecurityContextHolder.bindCredentials(null);
	}
	
	
	@Test
	public void testBindCredentials() {
		BasicCredentials credentials = new BasicCredentials();
		SecurityContextHolder.bindCredentials(credentials);
		Assert.assertNotNull(SecurityContextHolder.getCredentialsOrNull());
	}
	
	
	@Test(expected=CredentialsConflictException.class)
	public void testBindUserCredentialsConflict() {
		BasicCredentials credentials = new BasicCredentials();
		SecurityContextHolder.bindCredentials(credentials);
		SecurityContextHolder.bindCredentials(credentials);
	}
	
	
	@Test(expected=NoBoundCredentialsException.class)
	public void testGetNullUserCredentials() {
		SecurityContextHolder.getCredentials();
	}
}
