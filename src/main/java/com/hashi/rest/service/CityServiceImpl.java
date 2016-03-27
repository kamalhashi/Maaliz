package com.hashi.rest.service;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.hashi.repository.CityEnglishRepository;
import com.hashi.repository.CitySomaliRepository;
import com.hashi.rest.enums.LanguageType;


@Service("cityService")
public class CityServiceImpl  implements CityService {
	private final static Logger logger = Logger
			.getLogger(CityServiceImpl.class.getName());

	private CityEnglishRepository cityEnglishRepository;
	private CitySomaliRepository  citySomaliRepository;

	private MessageSource messageSource;



	@Autowired
	public CityServiceImpl(CityEnglishRepository cityEnglishRepository , CitySomaliRepository citySomaliRepository,MessageSource messageSource) {
		this.cityEnglishRepository= cityEnglishRepository;
		this.citySomaliRepository = citySomaliRepository;
		this.messageSource= messageSource;
	}

	@Override
	public List<?>  listCities(LanguageType language) {
		switch (language) {
		case en_US:
			return cityEnglishRepository.findAll();
		case so_SO:
			return  citySomaliRepository.findAll();
		default:
			break;
		}
		return null;
	}
	
	@Override
	public String getCityName(LanguageType language, Long cityId) {
		switch (language) {
		case en_US:
			return cityEnglishRepository.findByCityId(cityId).getCityName();
		case so_SO:
			return citySomaliRepository.findByCityId(cityId).getCityName();
		default:
			break;
		}
		return null;
	}
}

