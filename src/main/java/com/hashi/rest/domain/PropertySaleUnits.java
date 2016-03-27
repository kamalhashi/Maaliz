package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY_SALE_UNITS")
public class PropertySaleUnits extends Product implements Serializable {

	@Column(name = "PROPERTY_SIZE")
	private String propertySize;
	@Column(name = "OWNER_TYPE")
	private Integer ownerType;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}


	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

}
