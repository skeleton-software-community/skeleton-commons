package org.sklsft.commons.mapper.interfaces;

public interface Copier<T>  {
	
	/**
	 * Copy the content of obj2 in obj1.<br>
	 * If a field has the annotation {@see IgnoreCompare}, it will be copied only if copyIgnoredField = true.
	 * 
	 * @param obj1 object receiving the copy of object 2.
	 * @param obj2 object that will be copied.
	 * 
	 * @return the copied object.
	 */
	public T copy (T obj1, T obj2, boolean copyIgnoredFields);
	
	/**
	 * Copy the content of obj2 in obj1. If a field has the annotation {@see IgnoreCompare}, it will not be copied.
	 * 
	 * @param obj1 object receiving the copy of object 2.
	 * @param obj2 object that will be copied.
	 * 
	 * @return the copied object.
	 */
	public T copy (T obj1, T obj2);
	
}
