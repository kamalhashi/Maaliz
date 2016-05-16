package com.hashi.rest.resource;

import java.net.URI;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.enums.Role;
import com.hashi.rest.exception.UserNotFoundException;
import com.hashi.rest.service.UserService;
import com.hashi.rest.service.EmailService;



@RestController
@RequestMapping("/user")
public class UserResource {
	private final static Logger logger = Logger
			.getLogger(UserResource.class.getName());


	private UserService userService;
	private MessageSource message;
	private EmailService emailService;
	private UserRepository userRepository;

	@Autowired
	public UserResource(MessageSource message , EmailService emailService, UserService userService, UserRepository userRepository) {
		this.emailService= emailService;
		this.userService= userService;
		this.message = message;
		this.userRepository= userRepository;
	}	




	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public User getUserById(@PathVariable Long id) {
		User user = userService.findByUserId(id);
		if (user == null) {
			throw new UserNotFoundException(message.getMessage("userNotFoundException", null, LocaleContextHolder.getLocale()) + id);
		}
		return user;
	}

	@RequestMapping(value="/email/{email}", method=RequestMethod.GET)
	@PreAuthorize("permitAll")
	public Boolean isEmailExist(@PathVariable String email) {
		User user = userService.findByEmail(email);		
		return user==null ? true : false;
	}


	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize("permitAll")
	public ResponseEntity<User> creatUser(@RequestBody User user, UriComponentsBuilder ucb) {

		//check the user first and then save the user
		User savedUser = userService.createNewUser(user);
		emailService.sendEmailRegistration(savedUser);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri =
				ucb.path("/successRegistration/")
				//.path(String.valueOf(savedUser.getUserId()))
				.build()
				.toUri();
		headers.setLocation(locationUri);

		ResponseEntity<User> responseEntity =
				new ResponseEntity<User>(
						savedUser, headers, HttpStatus.CREATED);
		return responseEntity;	 
	}

	@RequestMapping( method=RequestMethod.PUT , headers="Accept=application/json")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>(HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value="/{id}",  method=RequestMethod.DELETE ,headers="Accept=application/json")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>(HttpStatus.OK);
		return responseEntity;
	}

	/*
	 * update user by specific filed.
	 */
	@RequestMapping(value="/{columnName}/{userId}/{data}", method=RequestMethod.PUT , headers="Accept=application/json")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Integer> updateUserByColumnName(@PathVariable String columnName,
			@PathVariable  Long userId, @PathVariable String data) {

		Integer updated= userService.updateUserByColumnName(userId, columnName, data);
		ResponseEntity<Integer> responseEntity =
				new ResponseEntity<Integer>(updated, HttpStatus.OK);
		return responseEntity;
	}

	/*
	 * is password same or equal the one stored in the database 
	 * 
	 */
	@RequestMapping(value="/isPasswordSame/{userId}/{password}", method=RequestMethod.GET , headers="Accept=application/json")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Boolean> isPasswordSame(@PathVariable  Long userId, @PathVariable String password) {
		return  new ResponseEntity<Boolean>(userService.isPasswordSame(password, userId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/loginSocial/{accessToken}", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public ResponseEntity<User>  socialLogin(@PathVariable  String accessToken) {
		System.out.println(accessToken);
	   
		Facebook facebook = new FacebookTemplate(accessToken);
		org.springframework.social.facebook.api.User profile = facebook.userOperations().getUserProfile();
		Set<SimpleGrantedAuthority> setOfAuthorities = new HashSet<SimpleGrantedAuthority>();
		
		
		setOfAuthorities.add(new SimpleGrantedAuthority("ROLE_FACEBOOK")); 
		
		User user = userService.findByEmail(profile.getEmail());
		
		if(user == null ){
		  user = new User();
		  user.setEmail(profile.getEmail());
		  user.setFirstname(profile.getFirstName());
		  user.setSurname(profile.getLastName());
		  user.setGender(profile.getGender());
		  userService.createNewSocialUser(user);
		}
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
			    AuthorityUtils.createAuthorityList(Role.ROLE_FACEBOOK.getUserRole()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);			
        return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	

	/*
	 * For angular, to check user logged in or not, return 401 if user not logged in
	 */
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {    
		return user;
	}
	
	
	/*
	 *Refresh categories this code should not be here
	 */
	@RequestMapping("/refreshSomaliCategories")
	@ResponseBody
	public void refreshSomaliCategories() {    
		System.out.println("refreshSomaliCategories");
		userRepository.refreshSomaliCategories();
	}
	
	/*
	 *Refresh categories this code should not be here
	 */
	@RequestMapping("/refreshEnglishCategories")
	@ResponseBody
	public void refreshEnglishCategories() {    
		System.out.println("refreshEnglishCategories");
		userRepository.refreshEnglishCategories();
	}

}
