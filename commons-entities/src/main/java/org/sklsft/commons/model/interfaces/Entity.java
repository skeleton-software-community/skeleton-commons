package org.sklsft.commons.model.interfaces;

import java.io.Serializable;

public interface Entity<T extends Serializable> extends Serializable {
	
	T getId();
	
	void setId(T id);
}
