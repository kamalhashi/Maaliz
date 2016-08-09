package com.hashi.rest.service;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.ProductPriority;
import com.hashi.search.vo.AdvanceSearch;

/**
 * @author: Kamal Weheliye
 *
 * Service to manage categories
 */

public interface  ProductService {


	/**
	 * Saving product 
	 * @return
	 */
	public Product saveProduct(Product product);
	
	/**
	 * Update product 
	 * @return
	 */
	public Long updateProduct(Product productEntity);


	/**
	 * get list of live products, also adjust the predicate or the condition based on the argument below	
	 * @param categoryId  Optional:get the product based on the categoryID 
	 * @param cityId      Optional: get the product based on cityId
	 * @param searchText  Optional: get the product based on text 
	 * @return
	 */
	public Page<Product> basicSearch(final Long categoryId, final Long cityId, String searchText, int pageIndex, String sortColumn, String sortDirection);
	
	
	/**
	 * advance search, filter products by fields
	 * @param categoryId  Optional:get the product based on the categoryID 
	 * @param cityId      Optional: get the product based on cityId
	 * @param searchText  Optional: get the product based on text 
	 * @return
	 */
	public Page<Product> advanceSearch(final Long categoryId, final  Long cityId, String searchText, int pageIndex, AdvanceSearch searchBasic, String sortColumn, String sortDirection);
	
	
	/**
	 * Delete product from Database and product Images from Amazon S3
	 * @param productId
	 * @return
	 */
	public boolean deleteProduct(Long productId);
	
	/**
	 * 
	 * @return List of featured products
	 */
	public List<Product> getFeaturedProducts();
	
	

	/**
	 * 
	 * @return Product by categoryId
	 */
	public Product findProductWithUserAndImagesByProductId(Long productId);
	
	
	/**
	 * 
	 * @return Product by categoryId
	 */
	public Product findProductWithUserByProductId(Long productId);
	
	
	/**
	 * 
	 * @return count by category id
	 */
	public long countProductsByCategoryId(Long categoryId, Long cityId);
	
	/**
	 * 
	 * @return count by category id
	 */
	public List<Object> countProductsByDepth(Long categoryId, Integer depth, LanguageType language);
	
	
	/**
	 * Find all products that user post , this for the my_ads page
	 */
	public List<Product> findProductsByUserId(Long userId);
	
	/*get all applicant who applied this job or product
	 * 
	 */
	public Product findApplicantsByProductId(Long productId);
	
	
	/*
	 * Changing image pending status to live 
	 * 
	 */
	public int updateImageStatusForProduct(Product product, ImageStatus imageStatus);
	 
	
	/*
	 * Changing product priority 
	 * 
	 */
	public int updatePriorityForProduct(Product product, ProductPriority productPriority);

	/*
	 * Find expired Products for purging products that expired
	 */
	public List<Product> findAllExpiredProducts(Date now);

	

}
