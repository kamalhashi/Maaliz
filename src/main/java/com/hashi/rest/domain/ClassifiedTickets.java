package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_TICKETS")
public class ClassifiedTickets extends Product {

	@Column(name = "NUMBER_TICKETS")
	private Integer numberOfTickets;

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	
}
