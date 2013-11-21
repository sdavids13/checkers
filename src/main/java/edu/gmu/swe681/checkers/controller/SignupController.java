package edu.gmu.swe681.checkers.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import edu.gmu.swe681.checkers.dto.UserRegistration;
import edu.gmu.swe681.checkers.model.User;
import edu.gmu.swe681.checkers.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {

	private Logger LOG = LoggerFactory.getLogger(SignupController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * Loads the page for adding a new User
	 * 
	 * @return the new User Page ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSignupForm() {
		return new ModelAndView("signup", "userRegistration", new UserRegistration());
	}

	/**
	 * Handles saving a new User to the App through the Admin portal
	 * 
	 * @param userName
	 *            the name of the User to be added
	 * @return a redirected ModelAndView back to the Admin Portal
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UserRegistration userRegistration, BindingResult result) {
		String username = userRegistration.getUsername();
		if (userService.getUser(username) != null) {
			result.addError(new FieldError("userRegistration", "username",
				"'" + username + "' has already been registered, please use a different value."));
		}

		if (result.hasErrors()) {
			return new ModelAndView("signup", "userRegistration", userRegistration);
		}
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(userRegistration.getPassword());

		userService.save(newUser);
		LOG.info("Created new user: " + username);
		
		return new ModelAndView(new RedirectView("/", true));
	}
}
