package edu.gmu.swe681.checkers.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.gmu.swe681.checkers.model.User;
import edu.gmu.swe681.checkers.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getUserHomepage(Principal user) {
		User currentUser = userService.getUser(user.getName());

		return new ModelAndView("profile", "user", currentUser);
	}
}
