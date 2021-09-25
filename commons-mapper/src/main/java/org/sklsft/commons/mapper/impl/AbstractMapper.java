package org.sklsft.commons.mapper.impl;

import org.sklsft.commons.api.exception.TechnicalError;
import org.sklsft.commons.mapper.interfaces.Mapper;

/**
 * An abstract {@link Mapper} implementation that uses clazz1(2).newInstance
 * @author Nicolas Thibault
 *
 * @param <T>
 * @param <U>
 */
public abstract class AbstractMapper<T, U> implements Mapper<T, U> {
	
	private Class<T> clazz1;
	private Class<U> clazz2;
	
	public AbstractMapper (Class<T> clazz1, Class<U> clazz2) {
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
	public abstract T mapFrom(T obj1, U obj2);	
	
	
	@Override
	public T mapFrom(U obj2) {
		try {
			return mapFrom(clazz1.newInstance(), obj2);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new TechnicalError(TechnicalError.ERROR_UNKNOWN, e);
		}
	}
	

	@Override
	public abstract U mapTo(T obj1, U obj2);
	

	@Override
	public U mapTo(T obj1) {
		try {
			return mapTo(obj1, clazz2.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new TechnicalError(TechnicalError.ERROR_UNKNOWN, e);
		}
	}
}
