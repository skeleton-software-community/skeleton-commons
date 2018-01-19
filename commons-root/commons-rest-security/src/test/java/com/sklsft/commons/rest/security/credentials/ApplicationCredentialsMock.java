package com.sklsft.commons.rest.security.credentials;

import java.io.Serializable;

public class ApplicationCredentialsMock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private String name;
	private String editor;
	
	
	/*
	 * getters and setters
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
}
