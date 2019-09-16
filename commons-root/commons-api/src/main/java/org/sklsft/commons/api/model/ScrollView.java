package org.sklsft.commons.api.model;

import java.io.Serializable;
import java.util.List;

public class ScrollView<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * properties
	 */
	/**
	 * global number of elements
	 */
	private Long size;
	/**
	 * number of filtered elements
	 */
	private Long count;
	private Long numberOfPages;
	private Long currentPage;	
	private List<T> elements;

	
	/*
	 * getters and setters
	 */
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getElements() {
		return elements;
	}
	public void setElements(List<T> elements) {
		this.elements = elements;
	}
}
