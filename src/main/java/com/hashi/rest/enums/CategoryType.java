package com.hashi.rest.enums;

public enum CategoryType {

	ALL_CATEGORIES(2);

	private int value;

	private CategoryType(int value) {
		this.value = value;
	}

	/*
	 * get the value of the enum type
	 * 
	 * @return value the real value of the enum type called
	 */
	public int getValue() {
		return value;
	}


}
