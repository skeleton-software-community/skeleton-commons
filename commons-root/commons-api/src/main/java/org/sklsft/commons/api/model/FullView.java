package org.sklsft.commons.api.model;

import java.io.Serializable;

/**
 * This class can be extended to represent the full view of an object<br>
 * It can be used as the result of a get method<br>
 * It basically consists of an id and a form which must be serializable
 * 
 * @author Nicolas Thibault
 *
 * @param <T> id
 * @param <U> form
 */
public class FullView<T extends Serializable, U extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * properties
	 */
	protected T id;
	protected U form;
	

	/*
	 * getters and setters
	 */
	public U getForm() {
		return form;
	}
	public void setForm(U form) {
		this.form = form;
	}
	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}
}
