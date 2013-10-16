package edu.gmu.swe681.checkers.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.gmu.swe681.checkers.model.User;

@Repository
public class UserDAO extends BaseDAO<User> {

	public List<User> getAllUsers() {
		TypedQuery<User> query = this.entityManager.createQuery("from User", User.class);
		return query.getResultList();
	}

}
