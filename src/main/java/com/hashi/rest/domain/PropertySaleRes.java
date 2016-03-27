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
@Table(name = "PROPERTY_SALE_RES")
public class PropertySaleRes extends Product implements Serializable{

	@Column(name = "PROPERTY_SIZE")
	private String propertySize;
	@Column(name = "NO_BEDROOMS")
	private Integer noBedrooms;
	@Column(name = "NO_BATHROOMS")
	private Integer noBathrooms;
	@Column(name = "OWNER_TYPE")
	private Double ownerType;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;

	

	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID",nullable=false)
	private List<SaleResExtras> propertyExtras;

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

	public Double getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Double ownerType) {
		this.ownerType = ownerType;
	}

	public List<SaleResExtras> getPropertyExtras() {
		return propertyExtras;
	}

	public void setPropertyExtras(List<SaleResExtras> propertyExtras) {
		this.propertyExtras = propertyExtras;
	}
	
	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
}
