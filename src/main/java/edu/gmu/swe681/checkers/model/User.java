package edu.gmu.swe681.checkers.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@SuppressWarnings("serial")
public class User implements Comparable<User>, UserDetails {

	@Id
	@Pattern(regexp="^[a-z0-9]{4,15}$", message="only lowercase alpha and numeric values accepted between 4 and 15 characters." )
	@Column(nullable = false, unique = true, length = 20)
	private String username;

	// Unable to perform bean validation for password field because it checks
	// when it persists the salted+hashed value (way to large of a value).
	@Column(nullable = false, length = 100)
	private String password;

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(User user) {
		return new CompareToBuilder()
			.append(getUsername(), user.getUsername())
			.toComparison();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

}
