package com.hashi.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLASSIFIED_TICKETS")
public class ClassifiedTickets extends Product {

	@Column(name = "FROM_CITY")
	private String from;
	@Column(name = "TO_CITY")
	private String to;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
