package org.sklsft.commons.mapper;

import java.util.Date;
import java.util.List;

public class Compared {

	/*
	 * properties
	 */
	private Long longField;
	private Date dateField;
	private String stringField;
	private Boolean booleanField;
	
	private Dummy dummy;
	
	private List<Fool> fools;
	
	private List<String> strings;

	
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
	public Boolean getBooleanField() {
		return booleanField;
	}
	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}
	public Dummy getDummy() {
		return dummy;
	}
	public void setDummy(Dummy dummy) {
		this.dummy = dummy;
	}
	public List<Fool> getFools() {
		return fools;
	}
	public void setFools(List<Fool> fools) {
		this.fools = fools;
	}
	public List<String> getStrings() {
		return strings;
	}
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
}
