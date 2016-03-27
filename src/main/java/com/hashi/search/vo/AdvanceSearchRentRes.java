package com.hashi.search.vo;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class AdvanceSearchRentRes extends AdvanceSearch {

	/* information related to product table */

	private Integer bedroomMin;
	private Integer bedroomMax;
	public Integer getBedroomMin() {
		return bedroomMin;
	}
	public Integer getBedroomMax() {
		return bedroomMax;
	}
	public void setBedroomMin(Integer bedroomMin) {
		this.bedroomMin = bedroomMin;
	}
	public void setBedroomMax(Integer bedroomMax) {
		this.bedroomMax = bedroomMax;
	}
}
