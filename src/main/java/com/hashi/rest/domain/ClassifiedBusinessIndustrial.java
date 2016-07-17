package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_BUSINESS")
public class ClassifiedBusinessIndustrial extends Product {

	@Column(name = "USAGE_ITEM")
	private Integer usageItem;
	@Column(name = "CONDITION_ITEM")
	private Integer conditionItem;
	@Column(name = "AGE_ITEM")
	private Integer ageItem;
	
	public Integer getUsageItem() {
		return usageItem;
	}
	public Integer getConditionItem() {
		return conditionItem;
	}
	public void setUsageItem(Integer usageItem) {
		this.usageItem = usageItem;
	}
	public void setConditionItem(Integer conditionItem) {
		this.conditionItem = conditionItem;
	}
	public Integer getAgeItem() {
		return ageItem;
	}
	public void setAgeItem(Integer ageItem) {
		this.ageItem = ageItem;
	}

}
