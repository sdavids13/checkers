package edu.gmu.swe681.checkers.service;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.gmu.swe681.checkers.config.BaseIntegrationTest;
import edu.gmu.swe681.checkers.model.User;

public class UserServiceIntegrationTest extends BaseIntegrationTest {

	@Autowired
	UserService userService;
	
	@Test
	public void testSaveUser(){
		String password = "password";
		
		User user = new User();
		user.setUsername("tester");
		user.setPassword(password);
		user = userService.save(user);
		
		Assert.assertNotNull(user.getUsername());
		
		User retrievedUser = userService.retrieve(user.getUsername());
		Assert.assertNotNull(retrievedUser);
		Assert.assertNotNull(retrievedUser.getUsername());
		Assert.assertNotNull(retrievedUser.getPassword());
		//Password should be changed since it is hashed and salted
		Assert.assertNotEquals(password, user.getPassword());
	}
	
	@Test
	public void testBadUsernames(){
		User user = new User();
		user.setPassword("password");
		
		try{
			user.setUsername("foo");
			userService.save(user);
			Assert.fail("The username value is too short and should fail.");
		} catch (ConstraintViolationException ex) {
		}
		
		try{
			user.setUsername("@f$oo<");
			userService.save(user);
			Assert.fail("The username value has special characters which should fail.");
		} catch (ConstraintViolationException ex) {
		}
		
		try{
			user.setUsername("Foo");
			userService.save(user);
			Assert.fail("The username value has uppercase characters which should fail.");
		} catch (ConstraintViolationException ex) {
		}
	}
}
