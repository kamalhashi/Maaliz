package com.hashi.rest.enums;

public enum LanguageType {

	so_SO("so_SO") ,
	en_US("en_US");
	
	private String language;

	private LanguageType(String language) {
		this.language = language;
	}

	/*
	 * get the value of the enum type
	 * 
	 * @return value the real value of the enum type called
	 */
	public String getLanguage() {
		return language;
	}

}
