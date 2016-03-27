package com.hashi.rest.enums;

public enum ProductPriority {

	PRIORITY_NORMAL(0) {
		public String description() {
			return "Priority Level (0)";
		}
	},
	PRIORITY_FEATURED(1) {
		public String description() {
			return "Priority Level (1)";

		}
	};
	

	private int value;

	private ProductPriority(int value) {
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
