package com.hashi.rest.service;



import java.util.List;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.stereotype.Service;


import com.hashi.config.ApplicationConfig;
import com.hashi.repository.LocationRepository;
import com.hashi.rest.domain.Locations;


@Service("locationService")
public class LocationServiceImpl implements LocationService {
	private final static Logger logger = Logger
			.getLogger(LocationServiceImpl.class.getName());

	private LocationRepository locationRepository;
	private MessageSource messageSource;
	private ApplicationConfig config;


	@Autowired
	public LocationServiceImpl(LocationRepository locationRepository,
			MessageSource messageSource, ApplicationConfig config) {
		this.locationRepository = locationRepository;
		this.messageSource = messageSource;
		this.config = config;
	}


	@Override
	public List<Locations> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}


}