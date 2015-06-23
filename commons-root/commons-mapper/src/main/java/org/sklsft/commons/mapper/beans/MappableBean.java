package org.sklsft.commons.mapper.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MappableBean<T> {

	public Class<T> beanClass;
	
	public Map<String,AccessibleField> accessibleFields;
	
	public Object getValue(String fieldName, T object) {
		
		AccessibleField accessibleField = accessibleFields.get(fieldName);
		if (accessibleField == null) {
			throw new IllegalArgumentException("No accessible field " + fieldName + " for class " + beanClass.getName());
		}
		
		Method getter = accessibleField.getter;
		
		try {
			return getter.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void setValue(String fieldName, Object fieldValue, T object) {
		
		AccessibleField accessibleField = accessibleFields.get(fieldName);
		if (accessibleField == null) {
			throw new IllegalArgumentException("No accessible field " + fieldName + " for class " + beanClass.getName());
		}
		
		Method setter = accessibleField.setter;
		
		try {
			setter.invoke(object, fieldValue);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
