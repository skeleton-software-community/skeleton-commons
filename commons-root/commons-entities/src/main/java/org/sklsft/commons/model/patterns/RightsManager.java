package org.sklsft.commons.model.patterns;

import java.io.Serializable;

import org.sklsft.commons.model.interfaces.Entity;

public interface RightsManager<T extends Entity<U>, U extends Serializable> {
	
	/**
	 * can access
	 */
	boolean canAccess(T obj);
	
	/**
	 * check can access
	 */
	void checkCanAccess(T obj);
	
	/**
	 * check can access all
	 */
	void checkCanAccess();
	
	/**
	 * can create
	 */
	boolean canCreate();
	
	/**
	 * check can create
	 */
	void checkCanCreate();

	/**
	 * can save
	 */
	boolean canSave(T obj);
	
	/**
	 * check can save
	 */
	void checkCanSave(T obj);

	/**
	 * can update
	 */
	boolean canUpdate(T obj);

	/**
	 * check can update
	 */
	void checkCanUpdate(T obj);

	/**
	 * can delete
	 */
	boolean canDelete(T obj);

	/**
	 * check can delete
	 */
	void checkCanDelete(T obj);
}
