package com.hashi.rest.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY_RENT_RES")
public class PropertyRentRes extends Product implements Serializable {

	@Column(name = "PROPERTY_SIZE")
	private String propertySize;
	@Column(name = "NO_BEDROOMS")
	private Integer noBedrooms;
	@Column(name = "NO_BATHROOMS")
	private Integer noBathrooms;
	@Column(name = "FURNISHED")
	private Integer furnished;
	@Column(name = "RENT_TIME")
	private Integer rentTime;
	@Column(name = "OWNER_TYPE")
	private Integer ownerType;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;


	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private List<RentResExtras> propertyExtras;

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public Integer getNoBedrooms() {
		return noBedrooms;
	}

	public void setNoBedrooms(Integer noBedrooms) {
		this.noBedrooms = noBedrooms;
	}

	public Integer getNoBathrooms() {
		return noBathrooms;
	}

	public void setNoBathrooms(Integer noBathrooms) {
		this.noBathrooms = noBathrooms;
	}

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

	public List<RentResExtras> getPropertyExtras() {
		return propertyExtras;
	}

	public void setPropertyExtras(List<RentResExtras> propertyExtras) {
		this.propertyExtras = propertyExtras;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
}
