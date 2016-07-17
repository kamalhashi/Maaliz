package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_COLLECTIBLES")
public class ClassifiedCollectibles extends Product implements Serializable {

	@Column(name = "AGE_ITEM")
	private Integer ageItem;
	@Column(name = "USAGE_ITEM")
	private Integer usageItem;
	@Column(name = "CONDITION_ITEM")
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
