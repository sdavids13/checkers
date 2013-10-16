package edu.gmu.swe681.checkers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.gmu.swe681.checkers.model.User;
import edu.gmu.swe681.checkers.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	private Logger LOG = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCurrentUserProfile() {
		return new ModelAndView("users", "users", userService.getAllUsers());
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ModelAndView getProfile(@PathVariable("username") String username) {
		User user = userService.getUser(username);
		
		return new ModelAndView("profile", "user", user);
	}
}
