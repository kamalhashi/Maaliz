package com.hashi.rest.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.ProfileProductRepository;
import com.hashi.repository.CarModelRepository;
import com.hashi.repository.NeighbourhoodRepository;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.ProfileProduct;
import com.hashi.rest.domain.CarsModel;
import com.hashi.rest.domain.Neighbourhood;


@Service("autoCompleteService")
public class AutoCompleteServiceImpl  implements AutoCompleteService {
	private final static Logger logger = Logger
			.getLogger(AutoCompleteServiceImpl.class.getName());


	private CarModelRepository carModelRepository;
	private NeighbourhoodRepository neighbourhoodRepository;



	
	@Autowired
	public AutoCompleteServiceImpl(CarModelRepository carModelRepository, NeighbourhoodRepository neighbourhoodRepository) {
		this.carModelRepository= carModelRepository;
		this.neighbourhoodRepository= neighbourhoodRepository;
	}

	@Override
	public List<CarsModel> carModels(Long makeId, String modelName) {
		 return carModelRepository.findByMakeIdAndModelNameLikeIgnoreCase(makeId, "%" + modelName + "%");	
	}

	@Override
	public List<Neighbourhood> neighbourhood(String neighbourhoodName) {		
		return neighbourhoodRepository.findByNeighbourhoodNameLikeIgnoreCase("%" + neighbourhoodName + "%");
	}

}

