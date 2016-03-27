package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOTOR_CAR_PARTS")
public class MotorCarParts extends Product {

	@Column(name = "AGE_ITEM")
	private Integer ageItem;

	@Column(name = "SELLER_TYPE")
	private Integer sellerType;

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getAgeItem() {
		return ageItem;
	}

	public void setAgeItem(Integer ageItem) {
		this.ageItem = ageItem;
	}
}
