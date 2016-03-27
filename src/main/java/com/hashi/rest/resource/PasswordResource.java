package com.hashi.rest.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.service.EmailService;
import com.hashi.rest.service.UserService;
import com.hashi.rest.service.VerificationService;
import com.hashi.rest.vo.PasswordRequest;

@RestController
@RequestMapping("/password")
@PreAuthorize("isAuthenticated()")
public class PasswordResource {
	@Autowired
	protected EmailService emailService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected VerificationService verificationService;



	private final static Logger logger = Logger
			.getLogger(EmailService.class.getName());

	@RequestMapping(value="/lostPassword/email/{email}", method=RequestMethod.PUT)
	@PreAuthorize("permitAll")
	public ResponseEntity<VerificationToken> sendLostPasswordToken(@PathVariable String email) {
		VerificationToken token=null;
		token  = verificationService.generateLostPasswordToken(email);
		emailService.sendEmailLostPasswordToken(userService.findByEmail(email), token);
		return new ResponseEntity<VerificationToken>(token, HttpStatus.CREATED);
	}


	@RequestMapping(value="/resetPassword/token/{token}",  method=RequestMethod.PUT)
	@PreAuthorize("permitAll")
	public ResponseEntity<Boolean>  resetPassword(@RequestBody PasswordRequest passwordRequest, @PathVariable String token) {
		logger.log(Level.INFO, "sendEmailToken password(): {0}  token: {1}",  new Object[]{ passwordRequest.getPassword(), token});
		VerificationToken verification = verificationService.resetPassword(token, passwordRequest);

		return	new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}	
}
