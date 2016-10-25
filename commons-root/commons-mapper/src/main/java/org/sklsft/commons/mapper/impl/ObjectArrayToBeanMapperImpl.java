package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.ObjectArrayToBeanMapper;

/**
 * Enables the copy of a bean from an object array
 * @author Nicolas Thibault
 *
 * @param <T>
 */
public class ObjectArrayToBeanMapperImpl<T> implements ObjectArrayToBeanMapper<T> {
	
	private final MappableBean<T> mappableBean;
	
	public ObjectArrayToBeanMapperImpl (Class<T> clazz) {
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}

	@Override
	public T mapFrom(T obj, Object[] objectArray, int startField) {
		
		for (int i = 0;i<objectArray.length;i++) {
			mappableBean.accessibleFields.get(i+startField).setValue(objectArray[i], obj);
		}
		
		return obj;
	}
	
	@Override
	public T mapFrom(T obj, Object[] objectArray) {
		
		return mapFrom(obj, objectArray, 0);
	}
}
