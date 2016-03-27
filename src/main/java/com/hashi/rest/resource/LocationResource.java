package com.hashi.rest.resource;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.service.EmailService;

@RestController
@RequestMapping("/locations")
public class LocationResource {
	

	private final static Logger logger = Logger
			.getLogger(LocationResource.class.getName());
	
	
	
}
