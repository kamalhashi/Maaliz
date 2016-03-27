package com.hashi.rest.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CITY")
public class CityEnglish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITY_ID", unique = false, nullable = false)
	private Long cityId;
	@Column(name = "CITY_NAME", unique = false, nullable = false)
	private String cityName;
	
	@JsonIgnore
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private CountryEnglish country;
	
	
	

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CountryEnglish getCountry() {
		return country;
	}

	public void setCountry(CountryEnglish country) {
		this.country = country;
	}
}
