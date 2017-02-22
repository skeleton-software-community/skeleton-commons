package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Mapper;

/**
 * A {@link Mapper} implementation based on field comparison
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <U>
 */
public class BasicMapperImpl<T, U> extends AbstractMapper<T, U> {
	
	private final MappableBean<T> mappableBean1;	
	private final MappableBean<U> mappableBean2;	
	
	public BasicMapperImpl (Class<T> clazz1, Class<U> clazz2) {
		super(clazz1, clazz2);
		mappableBean1 = MappableBeanFactory.getMappableBean(clazz1);
		mappableBean2 = MappableBeanFactory.getMappableBean(clazz2);
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
}
