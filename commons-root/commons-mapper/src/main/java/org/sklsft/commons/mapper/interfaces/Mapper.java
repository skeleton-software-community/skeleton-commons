package org.sklsft.commons.mapper.interfaces;

/**
 * A simple interface to define mapping standard functions
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <U>
 */
public interface Mapper<T, U> {
	
	T mapFrom (T obj1, U obj2);
	
	T mapFrom (U obj2);
	
	U mapTo (T obj1, U obj2);
	
	U mapTo (T obj1);
	
}
