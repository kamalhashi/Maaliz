package com.hashi.rest.enums;

public enum ProductExpiryDate {

	PRIORITY_NORMAL(15) {
		public String description() {
			return "Priority Level (0)";
		}
	},
	PRIORITY_FEATURED(30) {
		public String description() {
			return "Priority Level (1)";

		}
	};

	private int value;

	private ProductExpiryDate(int value) {
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
