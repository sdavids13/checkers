package edu.gmu.swe681.checkers.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.gmu.swe681.checkers.service.UserService;

@Controller
@RequestMapping("/games")
public class GamesController {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(GamesController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCurrentUserProfile() {
		return new ModelAndView("index", "users", "");
	}
	
	
}
