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

public interface  CityService {
	/**
	 * list country and its *cities*  based on language
	 * @param local
	 * @return the list we need is the cities
	 */
	public List<?> listCities(LanguageType language);
	
	public String getCityName(LanguageType language, Long cityId);

}
