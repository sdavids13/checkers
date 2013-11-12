package edu.gmu.swe681.checkers.controller;


import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
