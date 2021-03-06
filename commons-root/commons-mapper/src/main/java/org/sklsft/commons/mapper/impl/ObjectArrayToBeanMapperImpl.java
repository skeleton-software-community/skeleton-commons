package org.sklsft.commons.mapper.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
			//Oracle patch for integers mapping
			AccessibleField accessibleField = mappableBean.accessibleFields.get(i+startField);
			Object value = objectArray[i];
			if (Long.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (BigDecimal.class.isAssignableFrom(value.getClass())) {
					value = ((BigDecimal)value).longValue();
				}
			}
			if (Integer.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (BigDecimal.class.isAssignableFrom(value.getClass())) {
					value = ((BigDecimal)value).intValue();
				}
			}
			if (Short.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (BigDecimal.class.isAssignableFrom(value.getClass())) {
					value = ((BigDecimal)value).shortValue();
				}
			}
			if (Boolean.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (BigDecimal.class.isAssignableFrom(value.getClass())) {
					value = ((BigDecimal)value).shortValue()>0;
				}
			}
			//Oracle patch for dates mapping
			if (LocalDate.class.isAssignableFrom(accessibleField.fieldClass)) {
				if (Date.class.isAssignableFrom(value.getClass())) {
					value = ((Date)value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
