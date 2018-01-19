package com.sklsft.commons.rest.security.credentials;

import java.io.Serializable;

public class UserCredentialsMock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String firstName;
	private String lastName;
	
	
	/*
	 * getters and setters
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/*
	 * equals and hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		UserCredentialsMock other = (UserCredentialsMock) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	/*
	 * toString
	 */
	@Override
	public String toString() {
		return "UserCredentialsMock [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
