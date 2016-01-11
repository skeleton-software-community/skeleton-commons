package org.sklsft.commons.api.model;

import java.io.Serializable;

public class SelectItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	private Long id;
	private String label;
	
	
	/*
	 * constructors
	 */
	public SelectItem() {
		super();
	}
	
	public SelectItem(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	
	/*
	 * getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
