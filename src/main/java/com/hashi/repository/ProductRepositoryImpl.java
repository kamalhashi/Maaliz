package com.hashi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.ProductPriority;

public class ProductRepositoryImpl  implements ProductRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public int updateImageStatusForProduct(Product product, ImageStatus imageStatus){
	     Query query = entityManager.createQuery("update Product p set p.imageStatus =:imageStatus where p.productId =:productId");
		 query.setParameter("imageStatus", imageStatus);
		 query.setParameter("productId", product.getProductId());
		 return query.executeUpdate();
	 }
		
	@Transactional
    @CacheEvict(value = "featuredProductCache", allEntries=true)    
	@Override
	public int updatePriorityForProduct(Product product,
			ProductPriority productPriority) {
	     Query query = entityManager.createQuery("update Product p set p.productPriority =:productPriority where p.productId =:productId");
		 query.setParameter("productPriority", productPriority);
		 query.setParameter("productId", product.getProductId());
		 return query.executeUpdate();
	 }
}
