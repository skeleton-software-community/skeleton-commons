package org.sklsft.commons.mapper.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.Hibernate;
import org.sklsft.commons.api.annotations.compare.Ignored;
import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Copier;

public class EntityCopier<T> implements Copier<T> {

	private final MappableBean<T> mappableBean;
	
	private Class<T> clazz;
	
	private Map<Class<?>, Copier<?>> copiers = new HashMap<>();
	
	
	public EntityCopier (Class<T> clazz) {
		this.clazz = clazz;
		mappableBean = MappableBeanFactory.getMappableBean(clazz);
	}


	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public T copy(Object src, boolean copyIgnoredFields) {
		
		if (src == null) {
			return null;
		}
		
		if (!clazz.isAssignableFrom(src.getClass())) {
			throw new IllegalArgumentException("Cannot copy an instance of : " + src.getClass().getName() + " in a : " + clazz.getName());
		}
		
		T dest;
		try {
			dest = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Cannot instantiate a : " + clazz.getName());
		}

		for (AccessibleField accessibleField : mappableBean.accessibleFields) {
			if (copyIgnoredFields || !accessibleField.field.isAnnotationPresent(Ignored.class) || !accessibleField.field.isAnnotationPresent(Id.class)) {
				if (accessibleField.isSet) {					
					Copier<?> copier = getCopier(accessibleField.genericParameters.get(0));					
					Set srcCollection = (Set) accessibleField.getValue(src);											
					Set destCollection = new HashSet();
					for (Object obj:srcCollection) {
						destCollection.add(copier.copy(obj, copyIgnoredFields));
					}
					accessibleField.setValue(destCollection, dest);
						
				} else {
					if (accessibleField.field.isAnnotationPresent(ManyToOne.class) || accessibleField.field.isAnnotationPresent(OneToOne.class)) {
						Hibernate.initialize(accessibleField.getValue(src));
					}
						
					if ((accessibleField.field.isAnnotationPresent(ManyToOne.class) && CascadeType.ALL.equals(accessibleField.field.getAnnotation(ManyToOne.class).cascade())) || (accessibleField.field.isAnnotationPresent(OneToOne.class) && CascadeType.ALL.equals(accessibleField.field.getAnnotation(OneToOne.class).cascade()))) {
						Class<?> fieldClass = accessibleField.fieldClass;
						Copier<?> copier = getCopier(fieldClass);
						accessibleField.setValue(copier.copy(accessibleField.getValue(src), copyIgnoredFields), dest);
					 } else {
						 accessibleField.setValue(accessibleField.getValue(src), dest);
					 }
				}
			}
		}
		
		return dest;
	}
	
	
	@Override
	public T copy(Object src) {
		return copy(src, false);
	}
	
	private Copier<?> getCopier(Class<?> clazz) {
		if (!copiers.containsKey(clazz)) {
			copiers.put(clazz, new EntityCopier<>(clazz));
		}
		return copiers.get(clazz);
	}
}
