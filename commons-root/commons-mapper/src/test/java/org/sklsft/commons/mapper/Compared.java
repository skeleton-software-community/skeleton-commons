package org.sklsft.commons.mapper;

import java.util.Date;
import java.util.List;

import org.sklsft.commons.api.annotations.compare.DeepCompare;
import org.sklsft.commons.api.annotations.compare.IgnoreCompare;

public class Compared {

	/*
	 * properties
	 */
	private Long longField;
	private Date dateField;
	private String stringField;
	private Boolean booleanField;
	
	private List<String> strings;
	
	@DeepCompare
	private Dummy dummy;
	
	@DeepCompare
	private List<Fool> fools;
	
	@IgnoreCompare
	private String ignoredField;
	
	/*
	 * constructors
	 */
	public Compared(Long longField, Date dateField, String stringField,
			Boolean booleanField, List<String> strings, Dummy dummy,
			List<Fool> fools) {
		super();
		this.longField = longField;
		this.dateField = dateField;
		this.stringField = stringField;
		this.booleanField = booleanField;
		this.strings = strings;
		this.dummy = dummy;
		this.fools = fools;
	}
	
	public Compared() {
		
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
	public String getIgnoredField() {
		return ignoredField;
	}
	public void setIgnoredField(String ignoredField) {
		this.ignoredField = ignoredField;
	}
}
