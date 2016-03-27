package com.hashi.rest.service;


import java.util.List;
import java.util.Locale;

import com.hashi.rest.domain.CarsModel;
import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.Neighbourhood;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.enums.LanguageType;

/**
 * @author: Kamal Weheliye
 *
 * Service to manage categories
 */

public interface  AutoCompleteService {
	
	public List<CarsModel> carModels(Long makeId, String modelName);
	public List<Neighbourhood> neighbourhood(String neighbourhoodName);

}
