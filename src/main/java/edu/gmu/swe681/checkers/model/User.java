package edu.gmu.swe681.checkers.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Formula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@SuppressWarnings("serial")
public class User implements Comparable<User>, UserDetails {

	@Id
	@Column(nullable = false, unique = true, length = 20)
	private String username;

	// Unable to perform bean validation for password field because it checks
	// when it persists the salted+hashed value (way to large of a value).
	@Column(nullable = false, length = 100)
	private String password;
	
	@Formula("select count(*) from game g where g.winner = username")
	private int winCnt;
	
	@Formula("select count(*) from game g where g.winner is not null and g.winner != username and (g.black_player = username or g.red_player = username)")
	private int lossCnt;
	
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
	
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (obj.getClass() != getClass()) {
			return false;
		}
		
		return StringUtils.equals(getUsername(), ((User) obj).getUsername());
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getUsername()).toHashCode();
	}
	
	public int getWinCount() {
		return winCnt;
	}
	
	public int getLossCount() {
		return lossCnt;
	}
}
