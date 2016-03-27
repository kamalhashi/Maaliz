package com.hashi.rest.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "MOTOR_CARS")
public class MotorCar extends Product {
	@Column(name = "YEAR", unique = false, nullable = false)
	private Integer yearMade;
	@Column(name = "KILO_METERS", unique = false, nullable = false)
	private Integer kilometers;
	@Column(name = "BODY_TYPE", unique = false, nullable = false)
	private Integer bodyType;
	
	@Column(name = "MODEL", unique = false, nullable = false)
	private String model;
	@Column(name = "COLOUR", unique = false, nullable = false)
	private Integer colour;
	@Column(name = "TRANSMISSION_TYPE", unique = false, nullable = false)
	private Integer transmissionType;
	@Column(name = "FUEL_TYPE")
	private Integer fuelType;
	@Column(name = "SELLER_TYPE")
	private Integer sellerType;

	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL } , fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable=false)
	private List<CarExtras> carExtras;

	public Integer getYearMade() {
		return yearMade;
	}

	public void setYearMade(Integer yearMade) {
		this.yearMade = yearMade;
	}

	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}

	public Integer getBodyType() {
		return bodyType;
	}

	public void setBodyType(Integer bodyType) {
		this.bodyType = bodyType;
	}

	public Integer getColour() {
		return colour;
	}

	public void setColour(Integer colour) {
		this.colour = colour;
	}

	public Integer getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(Integer transmissionType) {
		this.transmissionType = transmissionType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getFuelType() {
		return fuelType;
	}

	public void setFuelType(Integer fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public List<CarExtras> getCarExtras() {
		return carExtras;
	}

	public void setCarExtras(List<CarExtras> carExtras) {
		this.carExtras = carExtras;
	}

}
