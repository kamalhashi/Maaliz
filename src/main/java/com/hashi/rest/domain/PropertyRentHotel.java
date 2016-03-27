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
@Table(name = "PROPERTY_RENT_HOTEL")
public class PropertyRentHotel extends Product implements Serializable {

	@Column(name = "WIFI")
	private Boolean wiFi;

	@Column(name = "PARKING")
	private Boolean parking;

	@Column(name = "RECEPTION")
	private Boolean reception;

	@Column(name = "RESTAURANT")
	private Boolean restaurant;

	@Column(name = "LAUNDRY")
	private Boolean laundry;
	
	@Column(name = "BREAKFAST")
	private Boolean breakfast;
	
	@Column(name = "ROOM_SERVICE")
	private Boolean roomService;
	
	@Column(name = "ELEVATOR")
	private Boolean elevator;
	
	
	@Column(name = "CONFERENCE_ROOM")
	private Boolean conferenceRoom;
	
	
	@Column(name = "FITNESS_FACILITIES")
	private Boolean fitnessFacilities;
	
	
	@Column(name = "AIRPORT_PICKUP")
	private Boolean airportPickup;
	
	@Column(name = "BABY_SITTING")
	private Boolean babySitting;
	
	
	@Column(name = "NEIGHBOURHOOD")
	private String neighbourhood;

	

	public Boolean getParking() {
		return parking;
	}

	public Boolean getReception() {
		return reception;
	}

	public Boolean getRestaurant() {
		return restaurant;
	}

	public Boolean getLaundry() {
		return laundry;
	}

	public void setParking(Boolean parking) {
		this.parking = parking;
	}


	public void setReception(Boolean reception) {
		this.reception = reception;
	}

	public void setRestaurant(Boolean restaurant) {
		this.restaurant = restaurant;
	}

	public void setLaundry(Boolean laundry) {
		this.laundry = laundry;
	}

	public Boolean getBreakfast() {
		return breakfast;
	}

	public Boolean getRoomService() {
		return roomService;
	}

	public Boolean getElevator() {
		return elevator;
	}

	public Boolean getConferenceRoom() {
		return conferenceRoom;
	}

	public Boolean getFitnessFacilities() {
		return fitnessFacilities;
	}

	public void setBreakfast(Boolean breakfast) {
		this.breakfast = breakfast;
	}

	public void setRoomService(Boolean roomService) {
		this.roomService = roomService;
	}

	public void setElevator(Boolean elevator) {
		this.elevator = elevator;
	}

	public void setConferenceRoom(Boolean conferenceRoom) {
		this.conferenceRoom = conferenceRoom;
	}

	public void setFitnessFacilities(Boolean fitnessFacilities) {
		this.fitnessFacilities = fitnessFacilities;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public Boolean getWiFi() {
		return wiFi;
	}

	public void setWiFi(Boolean wiFi) {
		this.wiFi = wiFi;
	}

	public Boolean getAirportPickup() {
		return airportPickup;
	}

	public Boolean getBabySitting() {
		return babySitting;
	}

	public void setAirportPickup(Boolean airportPickup) {
		this.airportPickup = airportPickup;
	}

	public void setBabySitting(Boolean babySitting) {
		this.babySitting = babySitting;
	}

}
