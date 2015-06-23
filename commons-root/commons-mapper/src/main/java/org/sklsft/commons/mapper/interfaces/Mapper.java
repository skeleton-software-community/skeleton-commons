package org.sklsft.commons.mapper.interfaces;

public interface Mapper<T, U> {
	
	T mapFrom (T obj1, U obj2);
	
	U mapTo (T obj1, U obj2);
	
}
