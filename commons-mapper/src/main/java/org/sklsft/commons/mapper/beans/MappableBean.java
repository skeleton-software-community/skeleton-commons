package org.sklsft.commons.mapper.beans;

import java.util.List;
import java.util.Map;

public class MappableBean<T> {

	public Class<T> beanClass;
	
	public List<AccessibleField> accessibleFields;
	
	public Map<String,AccessibleField> accessibleFieldsMap;
	
}
