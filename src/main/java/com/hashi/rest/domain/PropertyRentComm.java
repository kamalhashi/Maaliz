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
@Table(name = "PROPERTY_RENT_COMM")
public class PropertyRentComm extends Product implements Serializable {

	@Column(name = "PROPERTY_SIZE")
	private String propertySize;
	@Column(name = "FURNISHED")
	private Integer furnished;
	@Column(name = "RENT_TIME")
	private Integer rentTime;
	@Column(name = "OWNER_TYPE")
	private Integer ownerType;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;
	
	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "PRODUCT_ID", nullable=false)
	private List<RentCommExtras> propertyExtras;


	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
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

	public List<RentCommExtras> getPropertyExtras() {
		return propertyExtras;
	}

	public void setPropertyExtras(List<RentCommExtras> propertyExtras) {
		this.propertyExtras = propertyExtras;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

}
