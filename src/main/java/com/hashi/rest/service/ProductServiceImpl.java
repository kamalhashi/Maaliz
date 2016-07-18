package com.hashi.rest.service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.CityEnglishRepository;
import com.hashi.repository.ProductRepository;
import com.hashi.repository.ProductSpecification;
import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.LiveType;
import com.hashi.rest.enums.ProductPriority;
import com.hashi.search.vo.AdvanceSearch;
import com.hashi.util.ExpiryDate;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private final static Logger logger = Logger
			.getLogger(ProductServiceImpl.class.getName());

	private ProductRepository productRepository;
	private ApplicationConfig config;
	private CityEnglishRepository cityEnglishRepository;

	private static final int NUMBER_OF_PRODUCTS_PER_PAGE = 10;
	private final static String FOLDER_SUFFIX = "/";


	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
			ApplicationConfig config, CityEnglishRepository cityEnglishRepository) {
		this.productRepository = productRepository;
		this.config = config;
		this.cityEnglishRepository=cityEnglishRepository;
	}

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		product.setLiveType(LiveType.LIVE);
		product.setExpiryDate(ExpiryDate
				.calculateExpiryDate(product.getProductPriority()));
		Product savedProduct=  productRepository.saveAndFlush(product);
		return  savedProduct;
	}

	@Override
	@Transactional
	public Long updateProduct(Product productEntity){
		productEntity.setLiveType(LiveType.LIVE);
		productEntity.setExpiryDate(ExpiryDate
				.calculateExpiryDate(productEntity.getProductPriority()));
		Product product=  productRepository.save(productEntity);
		return  product.getProductId();
	}

	@Override
	@Transactional
	public boolean deleteProduct(Long productId) {
		productRepository.delete(productId);
		return true;
	}

	@Override
	public Page<Product> basicSearch(final Long categoryId, Long cityId,
			String searchText, int pageIndex, String sortColumn, String sortDirection) {
		// Passes the specification created by ProductSpecification class and
		// the page specification to the repository.
		return productRepository.findAll(ProductSpecification.searchBasic(
				categoryId, cityId, searchText, cityEnglishRepository),
				constructPageSpecification(pageIndex, sortColumn, sortDirection));
	}

	@Override
	public Page<Product> advanceSearch(Long categoryId,
			Long cityId, String searchText, int pageIndex,
			AdvanceSearch searchAdvance, String sortColumn, String sortDirection) {
		return productRepository.findAll(ProductSpecification
				.searchAdvance(categoryId, cityId, searchText,
						searchAdvance, cityEnglishRepository), constructPageSpecification(pageIndex, sortColumn, sortDirection));
	}

	/**
	 * Returns a new object which specifies the the wanted result page.
	 * 
	 * @param pageIndex
	 *            The index of the wanted result page
	 * @return
	 */
	private Pageable constructPageSpecification(int pageIndex, String sortColumn, String sortDirection) {
		Pageable pageSpecification = new PageRequest(pageIndex,
				NUMBER_OF_PRODUCTS_PER_PAGE, Sort.Direction.fromString(sortDirection), sortColumn);
		return pageSpecification;
	}

	@Override
	public List<Product> getFeaturedProducts() {		
		return productRepository.findByProductPriorityOrderByCreatedDesc(ProductPriority.PRIORITY_FEATURED);
	}

	@Override
	@Transactional( readOnly = true )
	public Product findProductWithUserAndImagesByProductId(Long productId) {
		return productRepository.findProductWithUserAndImagesByProductId(productId);
	}

	@Override
	@Transactional( readOnly = true )
	public Product findProductWithUserByProductId(Long productId) {
		Product product = productRepository.findProductWithImagesByProductId(productId);
		return product;
	}

	@Override
	public long countProductsByCategoryId(Long categoryId, Long cityId) {
		return productRepository.count(ProductSpecification.countProductsByCategoryId(categoryId, cityId, cityEnglishRepository));
	}

	@Override
	public List<Object> countProductsByDepth(Long categoryId, Integer depth, LanguageType language) {
		switch (language) {
		case en_US:
			return productRepository.countCategoriesEnglishProductsByDepth(categoryId, depth);
		case so_SO:
			return productRepository.countCategoriesSomaliProductsByDepth(categoryId, depth);
		default:
			break;
		}
		return null;
	}

	@Override
	public List<Product> findProductsByUserId(Long userId) {
		return productRepository.findProductsByUserUserId(userId);
	}

	@Override
	public Product findApplicantsByProductId(Long productId) {
		return productRepository.findProductWithProfilesByProductId(productId);
	}

	@Override
	public int updateImageStatusForProduct(Product product, ImageStatus imageStatus) {
		return productRepository.updateImageStatusForProduct(product, imageStatus);
	}

	@Override
	public int updatePriorityForProduct(Product product,
			ProductPriority productPriority) {
		return productRepository.updatePriorityForProduct(product, productPriority);
	}
}