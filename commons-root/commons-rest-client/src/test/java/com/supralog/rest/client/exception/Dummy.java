 package com.supralog.rest.client.exception;

public class Dummy {
	
	/*
	 * properties
	 */
	private Long longField;
	private String stringField;
	
	
	/*
	 * constructors
	 */
	
	public Dummy() {
		super();
	}
	
	public Dummy(Long longField,String stringField) {
		super();
		this.longField = longField;		
		this.stringField = stringField;
	}
		
	
	
	/*
	 * getters and setters
	 */
	public Long getLongField() {
		return longField;
	}
	public void setLongField(Long longField) {
		this.longField = longField;
	}	
	public String getStringField() {
		return stringField;
	}
	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((longField == null) ? 0 : longField.hashCode());
		result = prime * result
				+ ((stringField == null) ? 0 : stringField.hashCode());
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
		Dummy other = (Dummy) obj;
		if (longField == null) {
			if (other.longField != null)
				return false;
		} else if (!longField.equals(other.longField))
			return false;
		if (stringField == null) {
			if (other.stringField != null)
				return false;
		} else if (!stringField.equals(other.stringField))
			return false;
		return true;
	}
	
	
}