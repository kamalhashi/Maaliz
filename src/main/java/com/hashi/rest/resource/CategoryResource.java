package com.hashi.rest.resource;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.service.CategoryService;
import com.hashi.rest.service.EmailService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	private final static Logger logger = Logger
			.getLogger(CategoryResource.class.getName());
	
	private CategoryService categoryService;

	
	@Autowired
	public CategoryResource(CategoryService categoryService, MessageSource messageSource){
		this.categoryService= categoryService;
	}
    /**
     * List all categories 
     * @return
     */
	@RequestMapping(value="/root/categories/{language}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<?>  listRootCategories(@PathVariable("language") LanguageType language) {
		List mainCategories = categoryService.listRootCategories(language);
		
		ResponseEntity<?> responseEntity =
				new ResponseEntity(mainCategories, HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * List sub categories that belong to a particular category 
	 * @param language
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/{categoryId}/categories/{language}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<?>  listSubCategoriesByCategoryId(@PathVariable("categoryId") Long categoryId, @PathVariable("language") LanguageType language) {
		
		List subCategories = categoryService.listSubCategoriesByCategoryId(language, categoryId);
		
		ResponseEntity<?> responseEntity =
				new ResponseEntity(subCategories, HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * List root  categories based on category
	 * @param language
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/root/{categoryId}/categories/{language}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<?>  listRootCategoryByCategoryId(@PathVariable("categoryId") Long categoryId, @PathVariable("language") LanguageType language) {		
		List subCategories = categoryService.listRootCategoriesByCategoryId(language, categoryId);
		ResponseEntity<?> responseEntity =
				new ResponseEntity(subCategories, HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
	 * get a category object by category id
	 * @param language
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/category/{categoryId}/{language}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<?>  getCategoryByCategoryId(@PathVariable("categoryId") Long categoryId,  @PathVariable("language") LanguageType language) {		
		Object subCategories = categoryService.findCategoryByCategoryId(categoryId, language);
		ResponseEntity<?> responseEntity =
				new ResponseEntity(subCategories, HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
	 * return true  if category has child categories, otherwise false
	 * @param categoryId
	 * @return true or false
	 */
	@RequestMapping(value="/{categoryId}/hasChildCategories/{language}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public boolean  hasChildCategories(@PathVariable("categoryId") Long categoryId, @PathVariable("language") LanguageType language) {
		return categoryService.hasChildCategories(language, categoryId);
	}
}
