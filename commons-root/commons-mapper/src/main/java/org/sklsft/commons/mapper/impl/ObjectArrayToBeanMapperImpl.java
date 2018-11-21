package org.sklsft.commons.mapper.impl;

import java.math.BigDecimal;

import org.sklsft.commons.mapper.beans.AccessibleField;
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
			//Oracle patch for Long mapping
			AccessibleField accessibleField = mappableBean.accessibleFields.get(i+startField);
			Object value = objectArray[i];
			if (Long.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (BigDecimal.class.isAssignableFrom(value.getClass())) {
					value = ((BigDecimal)value).longValue();
				}
			}
			accessibleField.setValue(value, obj);
		}
		
		return obj;
	}
	
	@Override
	public T mapFrom(T obj, Object[] objectArray) {
		
		return mapFrom(obj, objectArray, 0);
	}
}
