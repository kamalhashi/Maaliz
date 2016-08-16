package com.hashi.rest.resource;


import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hashi.rest.service.ProfileProductService;
import com.hashi.rest.service.ProfileService;
import com.hashi.rest.service.UserService;
import com.hashi.rest.service.EmailService;
import com.hashi.rest.vo.EmailVO;


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
