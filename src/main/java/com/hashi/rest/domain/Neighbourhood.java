package com.hashi.rest.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEIGHBOURHOOD")
public class Neighbourhood {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEIGHBOURHOOD_ID", nullable = false, unique = true)
	private Long neighbourhoodId;
	@Column(name = "NEIGHBOURHOOD_NAME")
	private String neighbourhoodName;
	public Long getNeighbourhoodId() {
		return neighbourhoodId;
	}
	public String getNeighbourhoodName() {
		return neighbourhoodName;
	}
	public void setNeighbourhoodId(Long neighbourhoodId) {
		this.neighbourhoodId = neighbourhoodId;
	}
	public void setNeighbourhoodName(String neighbourhoodName) {
		this.neighbourhoodName = neighbourhoodName;
	}
}
