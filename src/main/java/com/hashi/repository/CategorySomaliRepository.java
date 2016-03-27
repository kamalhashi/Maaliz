package com.hashi.repository;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.rest.domain.CategorySomali;

@Repository

public interface CategorySomaliRepository extends JpaRepository<CategorySomali, Long> {
	/**
	 * find a specific category based on category id 
	 * @param categoryId
	 * @return
	 */
	@Cacheable("findCategoryByCategoryIdSomali")
	public CategorySomali findByCategoryId(Long categoryId);
	
	
	/**
	 * return a list of main root categories, the top categories are six 
	 * @return list of the top categories
	 */
	@Cacheable("listRootCategoriesSomali")
	@Query("SELECT Child FROM CategorySomali Child, CategorySomali Parent where Child.depth =Parent.depth +1 AND Parent.lft =1 ORDER BY Child.categoryId")
	public List<CategorySomali> listRootCategories();
	
	/**
	 *  return  a list of sub categories belong to a category, by using the selected categoryId
	 *  find related sub categories  
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT Child FROM CategorySomali Child, CategorySomali Parent WHERE Parent.categoryId = :categoryId  AND  Child.depth =Parent.depth +1 AND Child.lft BETWEEN Parent.lft AND Parent.rgt  ORDER BY Child.categoryId")
	@Cacheable("subCategoriesOfCategorySomali")
	public List<CategorySomali> listSubCategoriesOfCategory(@Param("categoryId") Long categoryId);
	
	/**
	 * list root categories of a category, traverse up to the root . 
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT Parent FROM CategorySomali Child, CategorySomali Parent WHERE Child.categoryId = :categoryId  AND Child.lft BETWEEN Parent.lft AND Parent.rgt AND Parent.categoryId <> 1 ORDER BY Parent.categoryId")
	@Cacheable("rootCategoriesOfCategorySomali")
	public List <CategorySomali> listRootCategoriesOfCategory(@Param("categoryId") Long categoryId);
	
	
	/**
	 * this will check if the categoryId, belongs to a job category
	 * this method will determine whether user has posted a job, if user has 
	 * posted a job then fetch the job and show it else show nothing the user.
	 * This method will only be used by jobBoard or the applicant resource
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT CASE WHEN COUNT(Child) > 0 THEN true ELSE false END FROM CategorySomali Child, CategorySomali Parent WHERE Child.categoryId = :categoryId  AND Child.lft BETWEEN Parent.lft AND Parent.rgt AND Parent.categoryId=7")
	public boolean  isCategoryBelongsJob(@Param("categoryId") Long categoryId);
	
	
	/**
	 *  return  true if category has child categories  
	 * @param categoryId
	 * @return
	 */
	@Query("SELECT CASE WHEN COUNT(Child) > 0 THEN true ELSE false END FROM CategorySomali Child, CategorySomali Parent WHERE Parent.categoryId = :categoryId  AND  Child.depth =Parent.depth +1 AND Child.lft BETWEEN Parent.lft AND Parent.rgt  ORDER BY Child.categoryId")
	@Cacheable("hasChildCategoriesSomali")
	public boolean hasChildCategories(@Param("categoryId") Long categoryId);
	
}