package com.hashi.rest.service;


import java.util.List;
import java.util.Locale;

import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.Locations;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;

/**
 * @author: Kamal Weheliye
 *
 * Service to manage categories
 */

public interface  LocationService {
	/**
	 * 
	 * 
	 * @return List of locations 
	 */
	public List<Locations> getLocations();
	
}
