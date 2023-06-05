package com.p6.payMyBuddy.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MySecurityUser extends User {

	private static final long serialVersionUID = 1L;
	
	public MySecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String firstName, String emailaddress) {
		super(username, password, enabled, accountNonExpired,credentialsNonExpired,accountNonLocked, authorities);
		this.username = firstName;
		this.email = emailaddress;
	}

	private String username;
	private String email;;

	
	@Override
	public String toString() {
		return "MySecurityUser firstName=" + username  + ", emailaddress=" + email + ", birthdate=" + "] " + super.toString();
	}
}
