package com.hashi.rest.enums;

public enum LiveType {

	LIVE(0) {
		public String description() {
			return "Live Add";
		}
	},
	NOT_LIVE(1) {
		public String description() {
			return "Not Live Add";

		}
	},
	//ORIGIAN VALUE =2 for testing puprose is set to 0
	UNDER_PROCESS(2) {
		public String description() {
			return "Under Process";
		}
	};

	
	private int value;

	private LiveType(int value) {
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
