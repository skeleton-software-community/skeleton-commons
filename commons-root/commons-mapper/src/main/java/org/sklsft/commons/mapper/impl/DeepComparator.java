package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.api.annotations.compare.IgnoreCompare;
import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Comparator;

public class DeepComparator implements Comparator {
	
	private final MappableBean<?> mappableBean;
	
	private Class<?> clazz;
	
	
	public DeepComparator (Class<?> clazz) {
		this.clazz = clazz;
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}

	@Override
	public boolean areEqual(Object obj1, Object obj2) {
		
		if (obj1 == null) {
			return obj2 == null;
		}
		
		if (obj2 == null) {
			return obj1 == null;
		}
		
		if (!clazz.isAssignableFrom(obj1.getClass()) || !clazz.isAssignableFrom(obj2.getClass())) {
			throw new IllegalArgumentException("you must compare objects of class : " + clazz.getName()
					+ "\n You compared " + obj1.getClass() + " with " + obj2.getClass());
		}		
		
		for (AccessibleField accessibleField:mappableBean.accessibleFields) {
			
			if (!accessibleField.field.isAnnotationPresent(IgnoreCompare.class)) {			
				AccessibleFieldComparator fieldComparator = new AccessibleFieldComparator(accessibleField);
				if (!fieldComparator.areEqual(obj1, obj2)) {
					return false;
				}
			}
		}
		
		return true;
		
	}
}
