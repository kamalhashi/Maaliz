package com.hashi.rest.enums;

public enum ImageStatus {

	IMAGE_LIVE(0),
	IMAGE_PENDING(1);

	private int value;

	private ImageStatus(int value) {
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
