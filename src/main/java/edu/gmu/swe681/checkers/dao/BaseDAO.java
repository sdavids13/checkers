package edu.gmu.swe681.checkers.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BaseDAO<T> {

	@PersistenceContext
	protected transient EntityManager entityManager;

	/**
	 * Creates a new entry in the database - from this reference
	 */
	protected void persist(T object) {
		this.entityManager.persist(object);
		this.flush();
	}

	/**
	 * Synchronize the persistence context to the underlying database.
	 */
	@Transactional
	protected void flush() {
		this.entityManager.flush();
	}

	/**
	 * Deletes this reference from the database
	 */
	@Transactional
	public void remove(T object) {
		T merged = this.entityManager.merge(object);
		this.entityManager.flush();
		this.entityManager.remove(merged);
	}

	/**
	 * Updates this reference from the database
	 * 
	 * @return The new saved reference
	 */
	@Transactional
	public T merge(T object) {
		T merged = this.entityManager.merge(object);
		this.flush();
		return merged;
	}

	@Transactional
	public T getById(Class<T> clazz, Object id) {
		return this.entityManager.find(clazz, id);
	}

}