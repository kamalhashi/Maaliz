package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_BABY_ITEMS")
public class ClassifiedBabyItems extends Product {

	@Column(name = "AGE_ITEM", unique = false, nullable = false)
	private Integer ageItem;
	@Column(name = "USAGE_ITEM", unique = false, nullable = false)
	private Integer usageItem;
	@Column(name = "CONDITION_ITEM", unique = false, nullable = false)
	private Integer conditionItem;
	public Integer getAgeItem() {
		return ageItem;
	}
	
	public Integer getConditionItem() {
		return conditionItem;
	}
	public void setAgeItem(Integer ageItem) {
		this.ageItem = ageItem;
	}
	
	public void setConditionItem(Integer conditionItem) {
		this.conditionItem = conditionItem;
	}
	public Integer getUsageItem() {
		return usageItem;
	}

	public void setUsageItem(Integer usageItem) {
		this.usageItem = usageItem;
	}	
}
