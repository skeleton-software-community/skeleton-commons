package com.sklsft.commons.rest.security.credentials;

import java.io.Serializable;

public class CredentialsMock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String applicationName;
	private String applicationEditor;
	private String userFirstName;
	private String userLastName;
	
	
	/*
	 * getters and setters
	 */
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationEditor() {
		return applicationEditor;
	}
	public void setApplicationEditor(String applicationEditor) {
		this.applicationEditor = applicationEditor;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	
	/*
	 * equals and hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationEditor == null) ? 0 : applicationEditor.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredentialsMock other = (CredentialsMock) obj;
		if (applicationEditor == null) {
			if (other.applicationEditor != null)
				return false;
		} else if (!applicationEditor.equals(other.applicationEditor))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		return true;
	}
	
	
	/*
	 * toString
	 */
	@Override
	public String toString() {
		return "CredentialsMock [applicationName=" + applicationName + ", applicationEditor=" + applicationEditor
				+ ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + "]";
	}
}
