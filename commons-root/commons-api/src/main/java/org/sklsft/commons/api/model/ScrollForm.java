package org.sklsft.commons.api.model;

import java.io.Serializable;

public class ScrollForm<F extends Serializable, S extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * properties
	 */
	private F filter;
	private S sorting;	
	private Long page = 1L;
	private Long elementsPerPage = 10L;
	
	
	/*
	 * getters and setters
	 */
	public F getFilter() {
		return filter;
	}
	public void setFilter(F filter) {
		this.filter = filter;
	}
	public S getSorting() {
		return sorting;
	}
	public void setSorting(S sorting) {
		this.sorting = sorting;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getElementsPerPage() {
		return elementsPerPage;
	}
	public void setElementsPerPage(Long elementsPerPage) {
		this.elementsPerPage = elementsPerPage;
	}	
}
