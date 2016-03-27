package com.hashi.rest.enums;

public enum CountryType {

	SOMALIA(1) {
		public String description() {
			return "Somalia";
		}
	};

	private int value;

	private CountryType(int value) {
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

	/*
	 * To write more descriptive and more details
	 */
	public abstract String description();

}
