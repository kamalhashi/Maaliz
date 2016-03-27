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
@Table(name ="PROPERTY_SALE_COMM")
public class PropertySaleComm extends Product implements Serializable {

	@Column(name ="PROPERTY_SIZE")
	private String propertySize;
	@Column(name = "OWNER_TYPE")
	private Integer ownerType;
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;
	
	
	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "PRODUCT_ID")
	private List<SaleCommExtras> propertyExtras;

	public List<SaleCommExtras> getPropertyExtras() {
		return propertyExtras;
	}

	public void setPropertyExtras(List<SaleCommExtras> propertyExtras) {
		this.propertyExtras = propertyExtras;
	}

	public String getPropertySize() {
		return this.propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public Integer getOwnerType() {
		return this.ownerType;
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
