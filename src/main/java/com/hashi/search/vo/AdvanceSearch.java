package com.hashi.search.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;



@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(value = AdvanceSearchCar.class),
    @JsonSubTypes.Type(value = AdvanceSearchCarParts.class),
    @JsonSubTypes.Type(value = AdvanceSearchBoats.class),
    @JsonSubTypes.Type(value = AdvanceSearchHeavyVehicle.class),
    @JsonSubTypes.Type(value = AdvanceSearchMotorcycle.class),
    @JsonSubTypes.Type(value = AdvanceSearchSaleRes.class),
    @JsonSubTypes.Type(value = AdvanceSearchSaleComm.class),
    @JsonSubTypes.Type(value = AdvanceSearchSaleUnits.class),
    @JsonSubTypes.Type(value = AdvanceSearchSaleLand.class),
    @JsonSubTypes.Type(value = AdvanceSearchRentRes.class),
    @JsonSubTypes.Type(value = AdvanceSearchRentComm.class),
    @JsonSubTypes.Type(value = AdvanceSearchRentRoom.class),
    @JsonSubTypes.Type(value = AdvanceSearchRentShortDaily.class),
    @JsonSubTypes.Type(value = AdvanceSearchRentShortMonthly.class),
    @JsonSubTypes.Type(value = AdvanceSearchClassified.class)

 
})
public  class AdvanceSearch implements Serializable {

	/* information related to product table */
	private Double priceFrom = null;
	private Double priceTo = null;

	public Double getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(Double priceFrom) {
		this.priceFrom = priceFrom;
	}

	public Double getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(Double priceTo) {
		this.priceTo = priceTo;
	}

	
}
