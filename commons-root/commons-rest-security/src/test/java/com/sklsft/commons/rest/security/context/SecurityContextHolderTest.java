package com.sklsft.commons.rest.security.context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.sklsft.commons.rest.security.context.SecurityContextHolder;
import org.sklsft.commons.rest.security.exception.CredentialsConflictException;
import org.sklsft.commons.rest.security.exception.NoBoundCredentialsException;

import com.sklsft.commons.rest.security.credentials.ApplicationCredentialsMock;
import com.sklsft.commons.rest.security.credentials.UserCredentialsMock;

public class SecurityContextHolderTest {
	
	@After
	public void clear() {
		SecurityContextHolder.unbindCredentials();
	}

	@Test(expected=NullPointerException.class)
	public void testBindNullApplicationCredentials() {
		SecurityContextHolder.bindApplicationCredentials(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testBindNullUserCredentials() {
		SecurityContextHolder.bindUserCredentials(null);
	}
	
	@Test
	public void testBindApplicationCredentials() {
		ApplicationCredentialsMock credentials = new ApplicationCredentialsMock();
		SecurityContextHolder.bindApplicationCredentials(credentials);
		Assert.assertNotNull(SecurityContextHolder.getApplicationCredentialsOrNull());
	}
	
	@Test
	public void testBindUserCredentials() {
		UserCredentialsMock credentials = new UserCredentialsMock();
		SecurityContextHolder.bindUserCredentials(credentials);
		Assert.assertNotNull(SecurityContextHolder.getUserCredentialsOrNull());
	}
	
	@Test(expected=CredentialsConflictException.class)
	public void testBindApplicationCredentialsConflict() {
		ApplicationCredentialsMock credentials = new ApplicationCredentialsMock();
		SecurityContextHolder.bindApplicationCredentials(credentials);
		SecurityContextHolder.bindApplicationCredentials(credentials);
	}
	
	@Test(expected=CredentialsConflictException.class)
	public void testBindUserCredentialsConflict() {
		UserCredentialsMock credentials = new UserCredentialsMock();
		SecurityContextHolder.bindUserCredentials(credentials);
		SecurityContextHolder.bindUserCredentials(credentials);
	}
	
	@Test(expected=NoBoundCredentialsException.class)
	public void testGetNullApplicationCredentials() {
		SecurityContextHolder.getApplicationCredentials();
	}
	
	@Test(expected=NoBoundCredentialsException.class)
	public void testGetNullUserCredentials() {
		SecurityContextHolder.getUserCredentials();
	}
}
