package org.sklsft.commons.rest.security.tokens.jwt;

import java.io.Serializable;
import java.util.Date;

public class BasicJwtBody implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String application;
	private String user;
	private Date expiryDate;
	
	/*
	 * getters and setters
	 */
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
