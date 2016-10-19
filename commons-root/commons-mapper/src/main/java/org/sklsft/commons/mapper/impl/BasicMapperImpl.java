package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Mapper;

public class BasicMapperImpl<T, U> implements Mapper<T, U> {
	
	private final MappableBean<T> mappableBean1;
	
	private final MappableBean<U> mappableBean2;	
	
	private Class<T> clazz1;
	private Class<U> clazz2;
	
	public BasicMapperImpl (Class<T> clazz1, Class<U> clazz2) {
		mappableBean1 = MappableBeanFactory.getMappableBean(clazz1);
		mappableBean2 = MappableBeanFactory.getMappableBean(clazz2);
		this.clazz1 = clazz1;
		this.clazz2 = clazz2;
	}
	
	public Class<T> getClazz1() {
		return clazz1;
	}

	public Class<U> getClazz2() {
		return clazz2;
	}
	

	@Override
	public T mapFrom(T obj1, U obj2) {		
		for (AccessibleField field1:mappableBean1.accessibleFieldsMap.values()) {
			String fieldName = field1.field.getName();
			AccessibleField field2 = mappableBean2.accessibleFieldsMap.get(fieldName);
			
			if (field2 != null && field1.isCompatibleWith(field2)) {
				field1.setValue(field2.getValue(obj2), obj1);
			}
		}
		
		return obj1;
	}
	
	
	@Override
	public T mapFrom(U obj2) {
		try {
			return mapFrom(clazz1.newInstance(), obj2);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new TechnicalError(TechnicalError.ERROR_UNKNOWN, e);
		}
	}
	

	@Override
	public U mapTo(T obj1, U obj2) {		
		for (AccessibleField field2:mappableBean2.accessibleFieldsMap.values()) {
			String fieldName = field2.field.getName();
			AccessibleField field1 = mappableBean1.accessibleFieldsMap.get(fieldName);
			
			if (field1 != null && field2.isCompatibleWith(field1)) {
				field2.setValue(field1.getValue(obj1), obj2);
			}
		}
		
		return obj2;
	}
	

	@Override
	public U mapTo(T obj1) {
		try {
			return mapTo(obj1, clazz2.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new TechnicalError(TechnicalError.ERROR_UNKNOWN, e);
		}
	}
}
