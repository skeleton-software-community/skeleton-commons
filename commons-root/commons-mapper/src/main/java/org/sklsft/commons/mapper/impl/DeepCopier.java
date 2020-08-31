package org.sklsft.commons.mapper.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.sklsft.commons.api.annotations.compare.Deep;
import org.sklsft.commons.api.annotations.compare.Ignored;
import org.sklsft.commons.mapper.beans.AccessibleField;
import org.sklsft.commons.mapper.beans.MappableBean;
import org.sklsft.commons.mapper.beans.MappableBeanFactory;
import org.sklsft.commons.mapper.interfaces.Copier;

public class DeepCopier<T> implements Copier<T> {

	private final MappableBean<T> mappableBean;
	
	private Class<T> clazz;
	
	private Map<Class<?>, Copier<?>> copiers = new HashMap<>();
	
	
	public DeepCopier (Class<T> clazz) {
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
			if (copyIgnoredFields || !accessibleField.field.isAnnotationPresent(Ignored.class)) {		
				
				if (accessibleField.field.isAnnotationPresent(Deep.class)) {
					
					if (accessibleField.isCollection) {
						
						Copier<?> copier = getCopier(accessibleField.genericParameters.get(0));
						
						Collection srcCollection = (Collection) accessibleField.getValue(src);
						try {							
							Collection destCollection = (Collection) srcCollection.getClass().newInstance();
							for (Object obj:(Collection)srcCollection) {
								destCollection.add(copier.copy(obj, copyIgnoredFields));
							}
							accessibleField.setValue(destCollection, dest);
							
						} catch (InstantiationException | IllegalAccessException e) {
							throw new IllegalArgumentException("Cannot instantiate a : " + srcCollection.getClass().getName());
						}
					} else if (accessibleField.isMap) {
						
						Copier<?> copier = getCopier(accessibleField.genericParameters.get(1));
						
						Map srcMap = (Map) accessibleField.getValue(src);
						try {							
							Map destMap = (Map) srcMap.getClass().newInstance();
							for (Object pair:srcMap.entrySet()) {
								Entry entry = (Entry)pair;
								destMap.put(entry.getKey(), copier.copy(entry.getValue()));
							}
							accessibleField.setValue(destMap, dest);
						} catch (InstantiationException | IllegalAccessException e) {
							throw new IllegalArgumentException("Cannot instantiate a : " + srcMap.getClass().getName());
						}
					} else {
						Class<?> fieldClass = accessibleField.fieldClass;
						Copier<?> copier = getCopier(fieldClass);
						accessibleField.setValue(copier.copy(accessibleField.getValue(src), copyIgnoredFields), dest);
					}
				} else {
					if (accessibleField.isCollection) {						
						Collection srcCollection = (Collection) accessibleField.getValue(src);
						try {							
							Collection destCollection = (Collection) srcCollection.getClass().newInstance();
							for (Object obj:srcCollection) {
								destCollection.add(obj);
							}
							accessibleField.setValue(destCollection, dest);
							
						} catch (InstantiationException | IllegalAccessException e) {
							throw new IllegalArgumentException("Cannot instantiate a : " + srcCollection.getClass().getName());
						}
						
					} else if (accessibleField.isMap) {
						Map srcMap = (Map) accessibleField.getValue(src);
						try {							
							Map destMap = (Map) srcMap.getClass().newInstance();
							for (Object pair:srcMap.entrySet()) {
								Entry entry = (Entry)pair;
								destMap.put(entry.getKey(), entry.getValue());
							}
							accessibleField.setValue(destMap, dest);
							
						} catch (InstantiationException | IllegalAccessException e) {
							throw new IllegalArgumentException("Cannot instantiate a : " + srcMap.getClass().getName());
						}
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
			copiers.put(clazz, new DeepCopier<>(clazz));
		}
		return copiers.get(clazz);
	}
	
}
