package org.sklsft.commons.model.patterns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sklsft.commons.api.exception.repository.ObjectNotFoundException;
import org.sklsft.commons.api.model.OrderType;
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
	 * count object list
	 */
	@Override
	public Long count() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<T> root = criteria.from(clazz);
		criteria.select(builder.count(root));		 
		return session.createQuery(criteria).getSingleResult();
	}

	/**
	 * load object list
	 */
	@Override
	public List<T> loadList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		criteria.select(root);
		List<Order> orders = new ArrayList<>();
		JpaCriteriaUtils.addOrder(builder, orders, root.get("id"), OrderType.DESC);
		criteria.orderBy(orders);
		return session.createQuery(criteria).getResultList();
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
	public T get(U id) {
		return this.sessionFactory.getCurrentSession().get(clazz, id);
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
