package org.sklsft.commons.model.patterns;

import java.io.Serializable;
import java.util.List;

import org.sklsft.commons.model.interfaces.Entity;

public interface BaseDao<T extends Entity<U>, U extends Serializable> {

	/**
	 * load object list
	 */
	List<T> loadList();

	/**
	 * load object list eagerly
	 */
	List<T> loadListEagerly();

	/**
	 * load object
	 */
	T load(U id);

	/**
	 * get object
	 */
	T get(U id);

	/**
	 * save object
	 */
	U save(T obj);

	/**
	 * delete object
	 */
	void delete(T obj);

	/**
	 * flush
	 */
	void flush();

	/**
	 * evict obj
	 */
	void evict(T obj);

	/**
	 * clear
	 */
	void clear();
}
