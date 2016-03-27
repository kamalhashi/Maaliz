package com.hashi.rest.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY_RENT_ROOM")
public class PropertyRentRoom extends Product implements Serializable {

	@Column(name = "FURNISHED")
	private Integer furnished;
	@Column(name = "RENT_TIME")
	private Integer rentTime;
	@Column(name = "OWNER_TYPE")
	private Integer ownerType;
	@Column(name = "NO_BATHROOMS")
	private Integer noBathrooms;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;
	
	public Integer getFurnished() {
		return furnished;
	}

	public void setFurnished(Integer furnished) {
		this.furnished = furnished;
	}

	public Integer getRentTime() {
		return rentTime;
	}

	public void setRentTime(Integer rentTime) {
		this.rentTime = rentTime;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public Integer getNoBathrooms() {
		return noBathrooms;
	}

	public void setNoBathrooms(Integer noBathrooms) {
		this.noBathrooms = noBathrooms;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

}
