package org.sklsft.commons.mapper.impl;

import java.io.Serializable;

import javax.inject.Inject;

import org.sklsft.commons.api.model.FullView;
import org.sklsft.commons.mapper.impl.BasicMapperImpl;
import org.sklsft.commons.mapper.interfaces.Mapper;
import org.sklsft.commons.model.interfaces.Entity;


/**
 * @author Nicolas Thibault
 *
 * @param <T> FullView<U, W> associated to the entity
 * @param <U> id of the full view and entity
 * @param <V> form associated to the entity
 * @param <W> Entity
 */
public class FullViewMapper<T extends FullView<U, V>, U extends Serializable, V extends Serializable, W extends Entity<U>> extends BasicMapperImpl<T, W> {
	
	public FullViewMapper(Class<T> clazz1, Class<W> clazz2) {
		super(clazz1, clazz2);
	}	
	
	@Inject
	private Mapper<V, W> formMapper;
		
	public Mapper<V, W> getFormMapper() {
		return formMapper;
	}
	public void setFormMapper(Mapper<V, W> formMapper) {
		this.formMapper = formMapper;
	}
	
	

	@Override
	public T mapFrom(T obj1, W obj2) {		
		
		obj1.setId(obj2.getId());
		
		obj1.setForm(formMapper.mapFrom(obj2));
				
		return obj1;
	}	
	

	@Override
	public W mapTo(T obj1, W obj2) {
		
		obj2 = formMapper.mapTo(obj1.getForm(), obj2);
		
		return obj2;
	}
}
