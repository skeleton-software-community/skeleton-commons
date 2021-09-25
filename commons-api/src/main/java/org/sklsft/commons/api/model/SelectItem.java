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
	private String key;
	private String label;
	
	
	/*
	 * constructors
	 */
	public SelectItem() {
		super();
	}
	
	public SelectItem(String key, String label) {
		super();
		this.key = key;
		this.label = label;
	}
	
	
	/*
	 * getters and setters
	 */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
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

	@Override
	public String toString() {
		return label;
	}
	
	
	
	
}
