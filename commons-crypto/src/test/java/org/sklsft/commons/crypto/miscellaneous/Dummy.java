package org.sklsft.commons.crypto.miscellaneous;

import java.util.Date;
import java.util.List;

public class Dummy {
	
	/*
	 * properties
	 */
	private Long longField;
	private Date dateField;
	private String stringField;
	private Boolean booleanField;
	
	private List<String> strings;
	
	@SuppressWarnings("unused")
	private String notAccessibleField;
	
	
	/*
	 * getters and setters
	 */
	public Long getLongField() {
		return longField;
	}
	public void setLongField(Long longField) {
		this.longField = longField;
	}
	public Date getDateField() {
		return dateField;
	}
	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}
	public String getStringField() {
		return stringField;
	}
	public void setStringField(String stringField) {
		this.stringField = stringField;
	}
	public Boolean isBooleanField() {
		return booleanField;
	}
	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}
	public List<String> getStrings() {
		return strings;
	}
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
}
