package com.hashi.rest.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class CountryEnglish {
	@Id
	@Column(name = "COUNTRY_ID", unique = false, nullable = false)
	private Integer countryId;
	@Column(name = "COUNTRY_NAME", unique = false, nullable = false)
	private String countryName;
	@Column(name = "COUNTRY_CODE", unique = false, nullable = false)
	private String countryCode;

	/* Country to City */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
	@OrderColumn(name = "CITY_ID")
	private List<CityEnglish> cities;

	@Column(name = "MESSAGE_KEY")
	private String messageKey;


	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<CityEnglish> getCities() {
		return cities;
	}

	public void setCities(List<CityEnglish> cities) {
		this.cities = cities;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
