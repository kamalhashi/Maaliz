package com.hashi.repository;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.hashi.rest.domain.Images;
import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.ProductPriority;

@Repository
@RestResource(exported = false)
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> , ProductRepositoryCustom {
	
	
	@Cacheable(value= "featuredProductCache")
	public List<Product> findByProductPriorityOrderByCreatedDesc(ProductPriority productPriority);
   
    @EntityGraph(value ="productWithImagesAndUser", type = EntityGraphType.LOAD)
	public Product findProductWithUserAndImagesByProductId(Long productId);
    
    
    //fetch profiles for a job.id {product.id}, only to fetch the applicants who appliad this product.id=job.id
    @EntityGraph(value ="productWithProfiles", type = EntityGraphType.LOAD)
	public Product findProductWithProfilesByProductId(Long productId);
    
    
    @EntityGraph(value ="productWithImages", type = EntityGraphType.LOAD)
    @Query("SELECT p FROM Product p WHERE p.productId=:productId")
    public Product findProductWithImagesByProductId(@Param("productId") Long productId);
    
    @Query(value="SELECT (SELECT InnerParent.category_name as categoryName FROM categories InnerChild, categories InnerParent "
    		+ "WHERE InnerChild.category_Id = child.category_Id "
    		+ "AND InnerChild.lft BETWEEN InnerParent.lft AND InnerParent.rgt AND InnerParent.category_Id <> 1 "
    		+ "AND InnerParent.DEPTH =:depth) as categoryName , count(parent.category_id) as countProduct "
    		+ "FROM categories parent , categories child, products p WHERE parent.category_id=:categoryId "
    		+ "AND child.lft BETWEEN parent.lft AND parent.rgt AND p.category_id= child.category_id"
    		+ " AND p.expiry_date >= curdate()  Group by categoryName" 
    		, nativeQuery = true)	
    public  List<Object> countCategoriesEnglishProductsByDepth(@Param("categoryId") Long categoryId, @Param("depth") Integer depth);
    
    @Query(value="SELECT (SELECT InnerParent.category_name as categoryName FROM categories_so InnerChild, categories_so InnerParent "
    		+ "WHERE InnerChild.category_Id = child.category_Id "
    		+ "AND InnerChild.lft BETWEEN InnerParent.lft AND InnerParent.rgt AND InnerParent.category_Id <> 1 "
    		+ "AND InnerParent.DEPTH =:depth) as categoryName , count(parent.category_id) as countProduct "
    		+ "FROM categories_so parent , categories_so child, products p WHERE parent.category_id=:categoryId "
    		+ "AND child.lft BETWEEN parent.lft AND parent.rgt AND p.category_id= child.category_id"
    		+ " AND p.expiry_date >= curdate()  Group by categoryName" 
    		, nativeQuery = true)	
    public  List<Object> countCategoriesSomaliProductsByDepth(@Param("categoryId") Long categoryId, @Param("depth") Integer depth);


    
    public  Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    
    public List<Product> findProductsByUserUserId(Long userId);

}