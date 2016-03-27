package com.hashi.rest.resource;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hashi.repository.CarModelRepository;
import com.hashi.rest.domain.CarsModel;
import com.hashi.rest.domain.Neighbourhood;
import com.hashi.rest.service.AutoCompleteService;


@RestController
@RequestMapping("/autocomplete")
public class AutoCompleteResource {
	private final static Logger logger = Logger.getLogger(AutoCompleteResource.class
			.getName());

	private AutoCompleteService autoCompleteService;
	

	@Autowired
	public AutoCompleteResource(AutoCompleteService autoCompleteService) {
		this.autoCompleteService= autoCompleteService;
	}

	/**
	 * List car models
	 * @return
	 */
	@RequestMapping(value="/carModels/{makeId}/{modelName}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<List<CarsModel>> carModels(@PathVariable Long makeId, @PathVariable String modelName ) {

		List<CarsModel> modelCarsList = autoCompleteService.carModels(makeId, modelName);
		ResponseEntity<List<CarsModel>> responseEntity =
				new ResponseEntity<List<CarsModel>>(modelCarsList, HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
	 * List neighbourhood
	 * @return
	 */
	@RequestMapping(value="/neighbourhood/{neighbourhoodName}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<List<Neighbourhood>> neighbourhood(@PathVariable String neighbourhoodName ) {

		List<Neighbourhood> neighbourhoodList = autoCompleteService.neighbourhood(neighbourhoodName);
		System.out.println("autoComplete" + neighbourhoodList.size());
		ResponseEntity<List<Neighbourhood>> responseEntity =
				new ResponseEntity<List<Neighbourhood>>(neighbourhoodList, HttpStatus.OK);
		return responseEntity;
	}
}

