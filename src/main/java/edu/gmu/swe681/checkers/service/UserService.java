package edu.gmu.swe681.checkers.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.gmu.swe681.checkers.dao.BaseDAO;
import edu.gmu.swe681.checkers.dao.UserDAO;
import edu.gmu.swe681.checkers.model.User;


@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private StandardPasswordEncoder encoder;
	
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}
	
	@Override
	protected BaseDAO<User> getDao() {
		return this.dao;
	}

	@Override
	protected Class<User> getModelClass() {
		return User.class;
	}
	
	public User getUser(String username) {
		return retrieve(username);
	}
	
	@Override
	public User save(User user){
		//Salt+Hash password to save in the database.
		user.setPassword(encoder.encode(user.getPassword()));
		return super.save(user);
	}
	
	public User getCurrentUser(){
		return (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}
	
	/**
	 * Removes the specified User from the Database
	 * @param user
	 */
	public void removeUser(User user) {
		this.dao.remove(user);
	}

}
