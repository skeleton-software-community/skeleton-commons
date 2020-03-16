package org.sklsft.commons.text.miscellanous;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="dummy")
@XmlType(name="dummy")
public class Dummy {
	
	/*
	 * properties
	 */
	@XmlElement(required=true)
	private String name;
	@XmlElement(name="fool")
	private Fool fool;
		
	
	/*
	 * getters and setters
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Fool getFool() {
		return fool;
	}
	public void setFool(Fool fool) {
		this.fool = fool;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fool == null) ? 0 : fool.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Dummy other = (Dummy) obj;
		if (fool == null) {
			if (other.fool != null)
				return false;
		} else if (!fool.equals(other.fool))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
