package com.hashi.rest.resource;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashi.config.ApplicationConfig;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.ProfileProduct;
import com.hashi.rest.domain.Profile;
import com.hashi.rest.domain.User;
import com.hashi.rest.exception.ProductCreationException;
import com.hashi.rest.exception.UserNotFoundException;
import com.hashi.rest.service.ProfileProductService;
import com.hashi.rest.service.ProfileService;
import com.hashi.rest.service.UserService;
import com.hashi.rest.service.EmailService;
import com.hashi.rest.vo.EmailReplyAdVO;
import com.hashi.rest.vo.EmailReplyJobVO;
import com.hashi.rest.vo.EmailVO;
import com.hashi.rest.vo.EmailVerificationVO;


@RestController
@RequestMapping("/email")
public class EmailResource {
	private final static Logger logger = Logger
			.getLogger(EmailResource.class.getName());


	private UserService userService;
	private ProfileService profileService;
	private MessageSource message;
	private EmailService emailService;
	private ProfileProductService profileProductService;

	@Autowired
	public EmailResource(MessageSource message , EmailService emailService, UserService userService, ProfileService profileService,
			ProfileProductService profileProductService) {
		this.emailService= emailService;
		this.userService= userService;
		this.message = message;
		this.profileService= profileService;
		this.profileProductService= profileProductService;
	}	


	@RequestMapping(value="/reply/ad/user/{userId}" , method=RequestMethod.POST)
	@PreAuthorize("permitAll")
	public ResponseEntity<Boolean> sendReplyAdByEmail(@RequestBody EmailVO emailVO, @PathVariable Long userId) {
		emailService.sendEmailReplyAd(emailVO, userId);
		return new ResponseEntity<Boolean> (true,HttpStatus.CREATED);		 
	}
	
	
	@RequestMapping(value="/enquiry" , method=RequestMethod.POST)
	@PreAuthorize("permitAll")
	public ResponseEntity<Boolean> sendEnquiry(@RequestBody EmailVO emailVO) {
		emailService.sendCustomerEnquiry(emailVO);
		return new ResponseEntity<Boolean> (true,HttpStatus.CREATED);		 
	}

	
}
