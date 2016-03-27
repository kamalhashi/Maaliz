package com.hashi.rest.service;


import java.util.List;
import java.util.Locale;

import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.enums.LanguageType;

/**
 * @author: Kamal Weheliye
 *
 * Service to manage categories
 */

public interface  CategoryService {
	/**
	 * retrieve root categories
	 * @param local
	 * @return List of main categories, the list of the categories would be based on the selected language.
	 */
	public List<?> listRootCategories(LanguageType language);
	
	/**
	 * list sub categories that belongs to a category 
	 * @param local
	 * @param categoryId is unique id to a category 
	 * @return the list of sub categories would be based on the selected category 
	 */
	public List<?> listSubCategoriesByCategoryId(LanguageType language, Long categoryId);
	
	
	/**
	 * List root categories that belongs to category 
	 * @param categoryId
	 * @return
	 */
	public List<?> listRootCategoriesByCategoryId(LanguageType language, Long categoryId);
	
	
	
	public Object  findCategoryByCategoryId(Long categoryId, LanguageType language);
	
	/**
	 * return true if category has child categories or sub categories
	 * @param categoryId
	 * @return
	 */
	public boolean hasChildCategories(LanguageType language, Long categoryId);
	

	/**
	 * this will check if the categoryId, belongs to a job category
	 * this method will determine whether user has posted a job, if user has 
	 * posted a job then fetch the job and show it else show nothing the user.
	 * This method will only be used by jobBoard or the applicant resource
	 * @param categoryId
	 * @return
	 */
	public boolean isCategoryBelongsJob(LanguageType language, Long categoryId);
	
}
