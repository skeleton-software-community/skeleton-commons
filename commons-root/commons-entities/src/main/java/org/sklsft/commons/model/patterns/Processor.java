package org.sklsft.commons.model.patterns;

import java.io.Serializable;

import org.sklsft.commons.model.interfaces.Entity;

public interface Processor<T extends Entity<U>, U extends Serializable> {

	/**
	 * process save
	 */
	U save(T obj);

	/**
	 * process update
	 */
	void update(T obj);

	/**
	 * process delete
	 */
	void delete(T obj);
}
