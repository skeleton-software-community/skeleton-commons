package org.sklsft.commons.mapper.interfaces;

public interface Copier<T>  {
	
	/**
	 * Copy an Object<br>
	 * If a field has the annotation {@see Ignored}, it will be copied only if copyIgnoredField = true.
	 * 
	 * @param src object that will be copied.
	 * 
	 * @return the copied object.
	 */
	public T copy (Object src, boolean copyIgnoredFields);
	
	/**
	 * Copy an Object<br>
	 * If a field has the annotation {@see Ignored}, it will not be copied.
	 * 
	 * @param src object that will be copied.
	 * 
	 * @return the copied object.
	 */
	public T copy (Object src);
	
}
