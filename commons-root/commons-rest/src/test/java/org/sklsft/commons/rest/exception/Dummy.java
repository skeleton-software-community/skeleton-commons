 package org.sklsft.commons.rest.exception;

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
}