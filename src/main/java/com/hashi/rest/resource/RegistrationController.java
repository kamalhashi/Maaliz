package com.hashi.rest.resource;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.hashi.repository.UserRepository;
import com.hashi.repository.VerificationTokenRepository;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.Role;
import com.hashi.rest.exception.DuplicateUserException;
import com.hashi.rest.exception.VerificationNotFoundException;

@RestController
public class RegistrationController {
	private final static Logger logger = Logger
			.getLogger(RegistrationController.class.getName());

	@Autowired
	private SimpleApplicationEventMulticaster simpleApplicationEventMulticaster;

	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private MessageSource message;
/*
 * 


	@RequestMapping(value = "/registerNewUser", method = RequestMethod.POST)
	public GeneralResponse registerNewUserAccount(@RequestBody User user,
			final HttpServletRequest request) throws Exception {
		//get the user from the database, user could be optional null or non-nul
		Optional<User> optUser= Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
		Optional<User> savedUser = null;


		//Lambda expression to find whether user is present, if user present then throw new exception 
		optUser.filter(x -> Optional.ofNullable(x).isPresent())
				.ifPresent(x -> {
					throw new DuplicateUserException ( message.getMessage("emailExistException", null, LocaleContextHolder.getLocale())+ optUser.get().getEmail());
				});

		System.out.println("About to store");
		//user is not present is empty
		if(!optUser.isPresent()){
			//assign the user a role
			UserRole userRole = new UserRole(user, Role.USER);
			userRole.setRole(Role.USER);
			//generate the verification token
			String token = UUID.randomUUID().toString();
			VerificationToken  verificationTokenEntity= new VerificationToken(user);
			

			//set and populate user properties
			userRole.setUser(user);
			verificationTokenEntity.setUser(user);
			//user.setToken(verificationTokenEntity);
			user.setUserRole(userRole);
			logger.info("Saving user");
			//save the suer 
			savedUser = Optional.ofNullable(userRepository.save(user));	
		};


		//once the user is saved send email-verification and store the token
		if (savedUser.isPresent()) {
			final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			//simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
			//simpleApplicationEventMulticaster.multicastEvent(new OnRegistrationCompleteEvent(savedUser.get(), LocaleContextHolder.getLocale() , appUrl));
			logger.info("Success returning ok");
			return new GeneralResponse(ResponseStatus.SUCCESS.getStatus());
		}
		return new GeneralResponse(ResponseStatus.FAILED.getStatus());
	}


	@RequestMapping(value = "/confirmRegistration", method = RequestMethod.GET)
	public GeneralResponse confirmRegistration
	(WebRequest request, Model model, @RequestParam("token") String token) {

		Optional<VerificationToken> verificationToken = Optional.ofNullable(verificationTokenRepository.findByToken(token));
		if (verificationToken.isPresent()) {
			checking if the verification time expired 
			Instant current= Instant.now();
			Instant previous= Instant.ofEpochMilli(verificationToken.get().getExpiryDate());
			Duration duration= Duration.between(previous, current);
			if(duration.toHours() <= 24){
				Optional<User> user =Optional.of(verificationToken.get().getUser());
				if(user.isPresent()){
					user.get().setEnabled(true);
					userRepository.save(user.get());
					return new GeneralResponse(ResponseStatus.SUCCESS.getStatus());
				}
			}else{// verification is expired
				throw new VerificationExpiredException (message.getMessage("verificationTokenExpiredException", null, LocaleContextHolder.getLocale())+
						verificationToken.get().getUser().getEmail());

			}
		} 
		return new GeneralResponse(ResponseStatus.FAILED.getStatus());	

	}*/

}
