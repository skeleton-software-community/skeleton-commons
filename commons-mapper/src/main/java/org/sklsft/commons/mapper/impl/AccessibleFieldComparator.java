package org.sklsft.commons.mapper.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.sklsft.commons.api.annotations.compare.Deep;
import org.sklsft.commons.mapper.beans.AccessibleField;

public class AccessibleFieldComparator {
	
	private final AccessibleField accessibleField;
	
	
	public AccessibleFieldComparator (AccessibleField accessibleField) {
		this.accessibleField = accessibleField;
	}


	@SuppressWarnings("rawtypes")
	public boolean areEqual(Object obj1, Object obj2) {		
		
		Object val1 = accessibleField.getValue(obj1);
		Object val2 = accessibleField.getValue(obj2);
		
		if (val1 == null) {
			return val2 == null;
		}
		
		if (val2 == null) {
			return val1 == null;
		}
		
		Class<?> clazz = accessibleField.fieldClass;
		
		if (accessibleField.field.isAnnotationPresent(Deep.class)) {
			
			if (accessibleField.isList) {
				Class<?> genericClass = accessibleField.genericParameters.get(0);				
				return areIteratorsDeeplyEqual(genericClass, ((Iterable)val1).iterator(), ((Iterable)val2).iterator());
			}
			
			if (accessibleField.isMap) {
				Class<?> valueClass = accessibleField.genericParameters.get(1);
				return areMapsDeeplyEqual(valueClass, ((Map)val1), ((Map)val2));
			}
			
			DeepComparator deepComparator = new DeepComparator(clazz);			
			return deepComparator.areEqual(val1, val2);			
		}
		
		if (accessibleField.isList) {
			return areIteratorsEqual(((Iterable)val1).iterator(), ((Iterable)val2).iterator());
		}
		
		if (accessibleField.isMap) {
			return areMapsEqual(((Map)val1), ((Map)val2));
		}
		
		return val1.equals(val2);		
	}


	@SuppressWarnings("rawtypes")
	private boolean areIteratorsDeeplyEqual(Class<?> iterableClass, Iterator ite1, Iterator ite2) {
		
		DeepComparator deepComparator = new DeepComparator(iterableClass);
		
		while (ite1.hasNext()) {
			if (!ite2.hasNext()) {
				return false;
			}
			
			Object obj1 = ite1.next();
			Object obj2 = ite2.next();
			
			if (!deepComparator.areEqual(obj1, obj2)) {
				return false;
			}			
		}
		
		return true;
	}


	@SuppressWarnings("rawtypes")
	private boolean areIteratorsEqual(Iterator ite1, Iterator ite2) {
		
		while (ite1.hasNext()) {
			if (!ite2.hasNext()) {
				return false;
			}
			
			Object obj1 = ite1.next();
			Object obj2 = ite2.next();
			
			if (!obj1.equals(obj2)) {
				return false;
			}			
		}
		
		return true;
	}


	@SuppressWarnings("rawtypes")
	private boolean areMapsDeeplyEqual(Class<?> valueClass, Map map1, Map map2) {
		
		DeepComparator deepComparator = new DeepComparator(valueClass);
		
		Set<?> keySet1 = map1.keySet();
		Set<?> keySet2 = map2.keySet();
		
		if (keySet1.size()!=keySet2.size()) {
			return false;
		}
		
		for (Object key:keySet1) {
			if (!deepComparator.areEqual(map1.get(key), map2.get(key))) {
				return false;
			}
		}
		return true;
	}
	
	
	@SuppressWarnings("rawtypes")
	private boolean areMapsEqual(Map map1, Map map2) {
		Set<?> keySet1 = map1.keySet();
		Set<?> keySet2 = map2.keySet();
		
		if (keySet1.size()!=keySet2.size()) {
			return false;
		}
		
		for (Object key:keySet1) {
			if (!map1.get(key).equals(map2.get(key))) {
				return false;
			}
		}
		return true;
	}
}
