package org.sklsft.commons.mapper.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.ObjectArrayToBeanMapper;

public class StringArrayToBeanMapperImpl<T> implements ObjectArrayToBeanMapper<T> {
	
	private final MappableBean<T> mappableBean;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public StringArrayToBeanMapperImpl (Class<T> clazz) {
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}

	@Override
	public T mapFrom(T obj, Object[] stringArray, int startField) {
		
		for (int i = 0;i<stringArray.length;i++) {
			AccessibleField accessibleField = mappableBean.accessibleFields.get(i + startField);
			mappableBean.setValue(accessibleField.field.getName(), getObjectFromString((String)stringArray[i], accessibleField.field), obj);
		}
		
		return obj;
	}
	
	@Override
	public T mapFrom(T obj, Object[] objectArray) {
		
		return mapFrom(obj, objectArray, 0);
	}
	
	private Object getObjectFromString(String value, Field field) {
		
		if (value.equals("")) {
			return null;
		}

		if (field.getType().equals(Date.class)) {
			try {
				Date date = format.parse(value);
				return date;
			} catch (ParseException e) {
				throw new IllegalArgumentException("Invalid string representation of a date", e);
			}
		}

		if (field.getType().equals(Double.class)) {
			return Double.valueOf(value);
		}

		if (field.getType().equals(Long.class)) {
			return Long.valueOf(value);
		}

		if (field.getType().equals(Boolean.class)) {
                return Boolean.valueOf(value);
		}
		
		return value;

	}
}
