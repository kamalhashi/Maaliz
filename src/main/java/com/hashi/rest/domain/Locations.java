package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "LOCATIONS")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
public class Locations {

	@Id
	private Long locationId;

	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "NEIGHBORHOOD")
	private String neighborhood;

	@Column(name = "CITY")
	private String city;

	@Column(name = "LATITUDE")
	private double latitude;

	@Column(name = "LONGITUDE")
	private double longtitude;
	
	
	
	/*
	 * The MapsId annotation ask Hibernate to copy the identifier from another
	 * associated entity. In the Hibernate jargon, it is known as a foreign
	 * generator but the JPA mapping reads better and is encouraged
	 */
	@MapsId
	@OneToOne(mappedBy = "location", fetch= FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	@JsonIgnore
	private Product product;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
