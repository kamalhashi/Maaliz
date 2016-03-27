package com.hashi.rest.service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.CategoryEnglishRepository;
import com.hashi.repository.CategorySomaliRepository;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.Role;
import com.hashi.rest.enums.MailType;
import com.hashi.rest.exception.AuthenticationException;
import com.hashi.rest.exception.CategoryNotFoundException;
import com.hashi.rest.exception.DuplicateUserException;

@Service("categoryService")
public class CategoryServiceImpl  implements CategoryService {
	private final static Logger logger = Logger
			.getLogger(CategoryServiceImpl.class.getName());

	private CategoryEnglishRepository englishCategoryRepository;
	private CategorySomaliRepository somaliCategoryRepository;
	private MessageSource messageSource;



	@Autowired
	public CategoryServiceImpl(CategoryEnglishRepository englishCategoryRepository, CategorySomaliRepository somaliCategoryRepository, MessageSource messageSource) {
		this.englishCategoryRepository = englishCategoryRepository;
		this.somaliCategoryRepository= somaliCategoryRepository;
		this.messageSource= messageSource;

	}

	@Override
	public List<?> listRootCategories(LanguageType language) {
		switch (language) {
		case en_US:
			return englishCategoryRepository.listRootCategories(); 
		case so_SO:
			return somaliCategoryRepository.listRootCategories(); 
		default:
			break;
		}
		throw new CategoryNotFoundException( messageSource.getMessage("categoryNotFoundException", null, LocaleContextHolder.getLocale())+ "Main Categories");
	}

	@Override
	public List<?> listSubCategoriesByCategoryId(LanguageType language, Long categoryId) {
		switch (language) {
		case en_US:
			return englishCategoryRepository.listSubCategoriesOfCategory(categoryId); 
		case so_SO:
			return somaliCategoryRepository.listSubCategoriesOfCategory(categoryId); 
		default:
			break;
		}	
		throw new CategoryNotFoundException( messageSource.getMessage("categoryNotFoundException", null, LocaleContextHolder.getLocale())+ "Main Categories");
	}

	@Override
	public List<?> listRootCategoriesByCategoryId(LanguageType language, Long categoryId) {

		//logger.log(Level.INFO, "Local : {0}  CategoryId: {1}",  new Object[]{ local.getLanguage() , categoryId});
		switch (language) {
		case en_US:
			return englishCategoryRepository.listRootCategoriesOfCategory(categoryId); 
		case so_SO:
			return somaliCategoryRepository.listRootCategoriesOfCategory(categoryId); 
		default:
			break;
		}	
		throw new CategoryNotFoundException( messageSource.getMessage("categoryNotFoundException", null, LocaleContextHolder.getLocale())+ "Main Categories");
	}

	@Override
	public Object findCategoryByCategoryId(Long categoryId, LanguageType language) {
		switch (language) {
		case en_US:
			return englishCategoryRepository.findByCategoryId(categoryId);
		case so_SO:
			return somaliCategoryRepository.findByCategoryId(categoryId); 
		default:
			break;
		}	
		throw new CategoryNotFoundException( messageSource.getMessage("categoryNotFoundException", null, LocaleContextHolder.getLocale())+ "findCategoryByCategoryId");
	}

	@Override
	public boolean hasChildCategories(LanguageType language, Long categoryId) {
		switch (language) {
		case en_US:
			return englishCategoryRepository.hasChildCategories(categoryId); 
		case so_SO:
			return somaliCategoryRepository.hasChildCategories(categoryId);
		default:
			break;
		}	
		throw new CategoryNotFoundException( messageSource.getMessage("categoryNotFoundException", null, LocaleContextHolder.getLocale())+ "findCategoryByCategoryId");

	}

	@Override
	public boolean isCategoryBelongsJob(LanguageType language, Long categoryId) {
		switch (language) {
		case en_US:
			return englishCategoryRepository.isCategoryBelongsJob(categoryId); 
		case so_SO:
			return somaliCategoryRepository.isCategoryBelongsJob(categoryId);
		default:
			break;
		}
		return false;
	}
}

