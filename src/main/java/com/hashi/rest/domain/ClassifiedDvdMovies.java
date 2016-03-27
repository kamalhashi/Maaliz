package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_DVDS")
public class ClassifiedDvdMovies extends Product implements Serializable {

	@Column(name = "AGE_OF_ITEM")
	private Integer ageItem;
	@Column(name = "USAGE_OF_ITEM")
	private Integer usageItem;
	@Column(name = "CONDITION_OF_ITEM")
	private Integer conditionItem;
	@Column(name = "RATING")
	private Integer ratingItem;
	
	public Integer getAgeItem() {
		return ageItem;
	}
	public Integer getUsageItem() {
		return usageItem;
	}
	public Integer getConditionItem() {
		return conditionItem;
	}
	public Integer getRatingItem() {
		return ratingItem;
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
	public void setRatingItem(Integer ratingItem) {
		this.ratingItem = ratingItem;
	}

	
}
