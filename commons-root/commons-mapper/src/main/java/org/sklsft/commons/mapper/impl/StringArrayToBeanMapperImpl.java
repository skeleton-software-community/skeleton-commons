package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.ObjectArrayToBeanMapper;

/**
 * Enables the copy of a bean from an object array
 * The object array is here assumed to be a string array
 * @author Nicolas Thibault
 *
 * @param <T>
 */
public class StringArrayToBeanMapperImpl<T> implements ObjectArrayToBeanMapper<T> {
	
	private final MappableBean<T> mappableBean;
	
	public StringArrayToBeanMapperImpl (Class<T> clazz) {
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}

	@Override
	public T mapFrom(T obj, Object[] stringArray, int startField) {
		
		for (int i = 0;i<stringArray.length;i++) {
			AccessibleField accessibleField = mappableBean.accessibleFields.get(i + startField);
			accessibleField.setValue(StringToObjectConverter.getObjectFromString((String)stringArray[i], accessibleField.fieldClass), obj);
		}
		
		return obj;
	}
	
	@Override
	public T mapFrom(T obj, Object[] objectArray) {
		
		return mapFrom(obj, objectArray, 0);
	}
	
	
}
