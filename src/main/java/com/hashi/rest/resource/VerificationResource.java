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
import com.hashi.rest.service.VerificationService;

@RestController
@RequestMapping("/verify")
public class VerificationResource {
	
	protected VerificationService verificationService;

	private final static Logger logger = Logger
			.getLogger(VerificationResource.class.getName());
	
	@Autowired
	public VerificationResource(VerificationService verificationService) {
		this.verificationService= verificationService;
	}

	@RequestMapping(value="/registerToken/{token}", method=RequestMethod.PUT)
	@PreAuthorize("permitAll")
	public ResponseEntity<String>  verifyToken(@PathVariable("token") String token) {
		logger.log(Level.INFO, "Verfication Method: {0}", token);
		VerificationToken verifyToken= verificationService.registerToken(token);
		
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>(HttpStatus.OK);
		return responseEntity;
	}
	
}
