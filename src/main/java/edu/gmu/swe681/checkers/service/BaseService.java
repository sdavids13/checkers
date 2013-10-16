package edu.gmu.swe681.checkers.service;

import org.springframework.transaction.annotation.Transactional;

import edu.gmu.swe681.checkers.dao.BaseDAO;


public abstract class BaseService<T> {

	protected abstract BaseDAO<T> getDao();
	protected abstract Class<T> getModelClass();
	
	/**
	 * @param object
	 *            the object to be saved and merged into the DAO
	 * @return the merged DAO and object
	 */
	@Transactional
	public T save(T object) {
		return getDao().merge(object);
	}

	/**
	 * 
	 * @param id
	 *            the id of the object
	 * @return the DAO based on the id parameter passed in
	 */
	public T retrieve(Object id) {
		return getDao().getById(getModelClass(), id);
	}

}
