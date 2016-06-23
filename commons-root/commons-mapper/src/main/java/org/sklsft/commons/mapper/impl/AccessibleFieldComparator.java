package org.sklsft.commons.mapper.impl;

import java.util.Iterator;

import org.sklsft.commons.api.annotations.compare.DeepCompare;
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
		
		Class<?> clazz = accessibleField.field.getType();
		
		if (accessibleField.field.isAnnotationPresent(DeepCompare.class)) {
			
			if (accessibleField.isIterable()) {
				Class<?> iterableClass = accessibleField.getIterableClass();
				
				return areIteratorsDeeplyEqual(iterableClass, ((Iterable)val1).iterator(), ((Iterable)val2).iterator());
			}
			
			DeepComparator deepComparator = new DeepComparator(clazz);
			
			return deepComparator.areEqual(val1, val2);
			
		}
		
		if (accessibleField.isIterable()) {
			return areIteratorsEqual(((Iterable)val1).iterator(), ((Iterable)val2).iterator());
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
}
