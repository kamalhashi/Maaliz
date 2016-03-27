package com.hashi.search.vo;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class AdvanceSearchHeavyVehicle extends AdvanceSearch {

	/* information related to product table */
	
	private Integer sellerType;
	private Integer yearMin;
	private Integer yearMax;
	private Integer kmFrom;
	private Integer kmTo;
	
	
	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

	public Integer getYearMin() {
		return yearMin;
	}

	public void setYearMin(Integer yearMin) {
		this.yearMin = yearMin;
	}

	public Integer getYearMax() {
		return yearMax;
	}

	public void setYearMax(Integer yearMax) {
		this.yearMax = yearMax;
	}

	public Integer getKmFrom() {
		return kmFrom;
	}

	public void setKmFrom(Integer kmFrom) {
		this.kmFrom = kmFrom;
	}

	public Integer getKmTo() {
		return kmTo;
	}

	public void setKmTo(Integer kmTo) {
		this.kmTo = kmTo;
	}
}
