package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOTOR_HEAVY_CAR")
public class MotorHeavyVehicle extends Product{

	@Column(name = "YEAR")
	private Integer yearMade;
	@Column(name = "KILOMETERS")
	private Integer kilometers;
	@Column(name = "SELLER_TYPE")
	private Integer sellerType;
	@Column(name = "MAKE")
	private String make;
	@Column(name = "MODEL")
	private String model;
	/*@Column(name = "CAPACITY_WEIGHT")
	private Double capacityWeight;*/
	@Column(name = "NO_CYLINDERS")
	private Integer noCylinders;
	@Column(name = "HORSE_POWER")
	private Integer horsePower;
	@Column(name = "FUEL_TYPE")
	private Integer fuelType;
	public Integer getYearMade() {
		return yearMade;
	}
	public Integer getKilometers() {
		return kilometers;
	}
	public Integer getSellerType() {
		return sellerType;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	
	public Integer getNoCylinders() {
		return noCylinders;
	}
	public Integer getHorsePower() {
		return horsePower;
	}
	
	public Integer getFuelType() {
		return fuelType;
	}
	public void setYearMade(Integer yearMade) {
		this.yearMade = yearMade;
	}
	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setNoCylinders(Integer noCylinders) {
		this.noCylinders = noCylinders;
	}
	public void setHorsePower(Integer horsePower) {
		this.horsePower = horsePower;
	}
	public void setFuelType(Integer fuelType) {
		this.fuelType = fuelType;
	}
	
	
}
