package org.sklsft.commons.api.model;

import java.io.Serializable;


/**
 * Tuple to be used for instance in JSF dropdown options
 * @author Alexandre RUPP
 *
 */
public class SelectItem implements Serializable, Comparable<SelectItem> {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		SelectItem other = (SelectItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public int compareTo(SelectItem selectItem) {
		if(this.label == null)
			if(selectItem.getLabel() == null)
				return 0;
			else
				return -1;
		else 
			if(selectItem.getLabel() == null)
				return 1; 
			else
				return this.label.compareTo(selectItem.getLabel());
	}
	
	
}
