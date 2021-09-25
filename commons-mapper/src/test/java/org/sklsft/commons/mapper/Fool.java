package org.sklsft.commons.mapper;

import java.util.Date;
import java.util.List;

public class Fool {
	
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
	 * constructor
	 */
	public Fool(Long longField, Date dateField, String stringField,
			Boolean booleanField, List<String> strings,
			String notAccessibleField) {
		super();
		this.longField = longField;
		this.dateField = dateField;
		this.stringField = stringField;
		this.booleanField = booleanField;
		this.strings = strings;
		this.notAccessibleField = notAccessibleField;
	}
	
	public Fool() {
		
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