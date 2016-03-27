package com.hashi.search.vo;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class AdvanceSearchCarParts extends AdvanceSearch {

	/* information related to product table */
	
	private Integer sellerType;
	
	
	public Integer getSellerType() {
		return sellerType;
	}

	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}

}
