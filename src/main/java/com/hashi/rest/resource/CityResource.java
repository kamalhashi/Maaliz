package com.hashi.rest.resource;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.service.CategoryService;
import com.hashi.rest.service.CityService;
import com.hashi.rest.service.EmailService;

@RestController
@RequestMapping("/cities")
public class CityResource {
	private final static Logger logger = Logger
			.getLogger(CityResource.class.getName());
	
	private CityService cityService;
    
	
	@Autowired
	public CityResource(CityService cityService, MessageSource messageSource){
		this.cityService= cityService;
	}
    /**
     * List all categories 
     * @return
     */
	@RequestMapping(value="/{language}",  method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<?>  listCities(@PathVariable("language") LanguageType language) {
		List listCities = cityService.listCities(language);
		
		ResponseEntity<?> responseEntity =
				new ResponseEntity(listCities, HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
     * get city name by city id
     * @return
     */
	@RequestMapping(value="/{cityId}/{language}",  method=RequestMethod.GET,  produces="application/json")
	@PreAuthorize("permitAll")
	@ResponseBody
	public  Map<String, Object>  getCityName(@PathVariable("cityId") Long cityId ,@PathVariable("language") LanguageType language) {
		String cityName = cityService.getCityName(language, cityId);
		System.out.println("language: "+ language + "cityId:" + cityId);
		HashMap<String, Object> result = new HashMap<>();
		result.put("cityName", cityName);
		return result;
	}
}
