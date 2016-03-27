package com.hashi.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hashi.rest.domain.ProfileProduct;


/**
 * @author: Kamal Weheliye
 *
 * Service to manage categories
 */

public interface  ProfileProductService {
	/**
	 * Save 
	 */
	public ProfileProduct save(Long userId, Long profileId, Long productId);
	
	/**
	 * has applicant applied this job before
	 * 
	 */
	public ProfileProduct isApplicantExist (Long productId, Long userId,  Long profileId);
	
	public Page<ProfileProduct> getProductProfilesByProductId(Long productId,  String sortColumn, String sortDirection,  int pageIndex);
	
}
