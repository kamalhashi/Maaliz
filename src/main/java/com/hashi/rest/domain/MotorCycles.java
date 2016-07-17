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
@Table(name = "MOTOR_CYCLES")
public class MotorCycles extends Product{
	@Column(name = "USAGE_ITEM")
	private Integer usageItem;
	@Column(name = "SELLER_TYPE")
	private Integer sellerType;
	
	public Integer getSellerType() {
		return sellerType;
	}
	
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getUsageItem() {
		return usageItem;
	}

	public void setUsageItem(Integer usageItem) {
		this.usageItem = usageItem;
	}
	
}
