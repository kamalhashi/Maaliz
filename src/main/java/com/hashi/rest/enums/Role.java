package com.hashi.rest.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	ROLE_USER("ROLE_USER"), 
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_FACEBOOK("ROLE_FACEBOOK");
	private final String userRole;

	private Role(String userRole) {
		this.userRole = userRole;
	}
	@JsonValue
	public String getUserRole() {
		return userRole;
	}
	
	
}
