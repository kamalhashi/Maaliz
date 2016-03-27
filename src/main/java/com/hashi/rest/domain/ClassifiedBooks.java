package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_BOOKS")
public class ClassifiedBooks extends Product {

	@Column(name = "AGE_OF_ITEM")
	private Integer ageItem;
	@Column(name = "USAGE_OF_ITEM")
	private Integer usageItem;
	@Column(name = "CONDITION_OF_ITEM")
	private Integer conditionItem;
	public Integer getAgeItem() {
		return ageItem;
	}
	public Integer getUsageItem() {
		return usageItem;
	}
	public Integer getConditionItem() {
		return conditionItem;
	}
	public void setAgeItem(Integer ageItem) {
		this.ageItem = ageItem;
	}
	public void setUsageItem(Integer usageItem) {
		this.usageItem = usageItem;
	}
	public void setConditionItem(Integer conditionItem) {
		this.conditionItem = conditionItem;
	}	
}
