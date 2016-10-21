package org.sklsft.commons.model.patterns;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.sklsft.commons.api.exception.repository.ObjectNotFoundException;
import org.sklsft.commons.model.interfaces.Entity;

public abstract class BaseDaoImpl<T extends Entity<U>, U extends Serializable> implements BaseDao<T, U> {

	/*
	 * resources injected with spring
	 */
	@Inject
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Class<T> clazz;
	

	/**
	 * constructor with the correct class to handle
	 */
	public BaseDaoImpl(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	/**
	 * load object list
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> loadList() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(clazz);
		return criteria.list();
	}

	/**
	 * load object list eagerly
	 */
	@Override
	public abstract List<T> loadListEagerly();

	/**
	 * load object
	 */
	@Override
	public T load(U id) {
		T obj = get(id);
		if (obj == null) {
			throw new ObjectNotFoundException(clazz.getSimpleName() + ".notFound");
		} else {
			return obj;
		}
	}

	/**
	 * get object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(U id) {
		return (T) this.sessionFactory.getCurrentSession().get(clazz, id);
	}

	/**
	 * save object
	 */
	@Override
	@SuppressWarnings("unchecked")
	public U save(T obj) {
		return (U) this.sessionFactory.getCurrentSession().save(obj);
	}

	/**
	 * delete object
	 */
	@Override
	public void delete(T obj) {
		this.sessionFactory.getCurrentSession().delete(obj);
	}

	/**
	 * flush
	 */
	@Override
	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	/**
	 * evict obj
	 */
	@Override
	public void evict(T obj) {
		sessionFactory.getCurrentSession().evict(obj);
	}

	/**
	 * clear
	 */
	@Override
	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
}
