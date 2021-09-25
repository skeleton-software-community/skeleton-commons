package org.sklsft.commons.crypto.miscellaneous;

import java.util.Date;

public class TestObject {

	/*
	 * properties
	 */
	private String stringField;
	private Date dateField;
	
	
	/*
	 * getters and setters
	 */
	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public Date getDateField() {
		return dateField;
	}

	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}
	
	
	/*
	 * no arg constructor
	 */
	public TestObject() {
		
	}

	
	/*
	 * constructor
	 */
	public TestObject(String stringField, Date dateField) {
		super();
		this.stringField = stringField;
		this.dateField = dateField;
	}

	@Override
	public String toString() {
		return "TestObject [stringField=" + stringField + ", dateField=" + dateField + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TestObject)) {
			return false;
		}
		return (stringField.equals(((TestObject)other).stringField) && dateField.equals(((TestObject)other).dateField));
	}
}

