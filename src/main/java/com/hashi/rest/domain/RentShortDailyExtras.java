package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RENT_SMONTHLY_EXTRAS")
public class RentShortDailyExtras implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXTRA_ID", nullable = false, unique = true)
	private Integer extraId;
	@Column(name = "EXTRA_VALUE", nullable = false, unique = true)
	private Integer extraValue;
	
	public Integer getExtraId() {
		return extraId;
	}

	public void setExtraId(Integer extraId) {
		this.extraId = extraId;
	}

	public Integer getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(Integer extraValue) {
		this.extraValue = extraValue;
	}
}
