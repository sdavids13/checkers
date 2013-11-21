package edu.gmu.swe681.checkers.dto;

import javax.validation.constraints.Pattern;

public class UserRegistration {

	@Pattern(regexp="^[a-z0-9]{4,15}$", message="only lowercase alpha and numeric values accepted between 4 and 15 characters." )
	private String username;
	
	
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\S{6,20})", message="must be between 6 and 20 characters that contains at least one of each type of character: lowercase alpha, uppercase alpha, and a numeric value." )
	private String password;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
