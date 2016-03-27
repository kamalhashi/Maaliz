package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOTOR_BOATS")
public class MotorBoats extends Product {

	@Column(name = "AGE_OF_ITEM")
	private Integer ageItem;
	@Column(name = "WARRANTY_OF_ITEM")
	private Integer warrantyItem;
	@Column(name = "SELLER_TYPE")
	private Integer sellerType;
	@Column(name = "LENGTH_BOAT")
	private Integer length;
	public Integer getAgeItem() {
		return ageItem;
	}
	
	public Integer getWarrantyItem() {
		return warrantyItem;
	}
	public Integer getSellerType() {
		return sellerType;
	}
	public Integer getLength() {
		return length;
	}
	public void setAgeItem(Integer ageItem) {
		this.ageItem = ageItem;
	}
	public void setWarrantyItem(Integer warrantyItem) {
		this.warrantyItem = warrantyItem;
	}
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
}
