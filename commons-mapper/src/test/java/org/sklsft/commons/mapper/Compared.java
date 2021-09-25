package org.sklsft.commons.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sklsft.commons.api.annotations.compare.Deep;
import org.sklsft.commons.api.annotations.compare.Ignored;

public class Compared {

	/*
	 * properties
	 */
	private Long longField;
	private Date dateField;
	private String stringField;
	private Boolean booleanField;
	
	private List<String> strings;
	
	private Map<String, String> stringMap;
	
	@Deep
	private Dummy dummy;
	
	@Deep
	private List<Fool> fools;
	
	@Deep
	private Map<String, Fool> foolMap;
	
	@Ignored
	private String ignoredField;
	
	/*
	 * constructors
	 */
	public Compared() {
		super();
	}
	
	
	public Compared(Long longField, Date dateField, String stringField, Boolean booleanField, List<String> strings,
			Map<String, String> stringMap, Dummy dummy, List<Fool> fools, Map<String, Fool> foolMap) {
		super();
		this.longField = longField;
		this.dateField = dateField;
		this.stringField = stringField;
		this.booleanField = booleanField;
		this.strings = strings;
		this.stringMap = stringMap;
		this.dummy = dummy;
		this.fools = fools;
		this.foolMap = foolMap;
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
	public List<String> getStrings() {
		return strings;
	}
	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
	public Map<String, String> getStringMap() {
		return stringMap;
	}
	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
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
	public Map<String, Fool> getFoolMap() {
		return foolMap;
	}
	public void setFoolMap(Map<String, Fool> foolMap) {
		this.foolMap = foolMap;
	}
	public String getIgnoredField() {
		return ignoredField;
	}
	public void setIgnoredField(String ignoredField) {
		this.ignoredField = ignoredField;
	}
}
