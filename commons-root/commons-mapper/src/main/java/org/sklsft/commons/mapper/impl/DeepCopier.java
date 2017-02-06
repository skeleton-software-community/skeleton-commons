package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.api.annotations.compare.IgnoreCompare;
import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Copier;

public class DeepCopier<T> implements Copier<T> {

	private final MappableBean<?> mappableBean;
	
	private Class<?> clazz;
	
	
	public DeepCopier (Class<?> clazz) {
		this.clazz = clazz;
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}


	@Override
	public T copy(T dest, T src, boolean copyIgnoredFields) {
		
		if (dest == null) {
			throw new IllegalArgumentException("Argument 1 MUST NOT be empty.");
		}
		
		if (src == null) {
			throw new IllegalArgumentException("Argument 2 MUST NOT be empty.");
		}
		
		if (!clazz.isAssignableFrom(dest.getClass()) || !clazz.isAssignableFrom(src.getClass())) {
			throw new IllegalArgumentException("Arguments MUST be of class : " + clazz.getName());
		}		

		for (AccessibleField accessibleField : mappableBean.accessibleFields) {
			if (copyIgnoredFields || !accessibleField.field.isAnnotationPresent(IgnoreCompare.class)) {		
				accessibleField.setValue(accessibleField.getValue(src), dest);
			}
		}
		
		return dest;
	}
	
	
	@Override
	public T copy(T dest, T src) {
		return copy(dest, src, false);
	}
	
}
