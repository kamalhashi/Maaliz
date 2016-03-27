package com.hashi.rest.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.hashi.rest.domain.UserRole;

public class CustomUser extends  org.springframework.security.core.userdetails.User {
	
	private final Long userId;
	private final String firstname;
	private final String telephone;


	public CustomUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Long userId, String firstname, String telephone) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userId= userId;
		this.firstname=firstname;
		this.telephone= telephone;
	}

	public Long getUserId() {
		return userId;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public static Set<GrantedAuthority> setUserAuthorities(Set<UserRole> roles) {
        Set<GrantedAuthority> setOfAuthorities = new HashSet<GrantedAuthority>();
        for (UserRole role : roles) {
            setOfAuthorities.add(new SimpleGrantedAuthority(role.getRole().getUserRole()));
        }
        return setOfAuthorities;
    }
}
