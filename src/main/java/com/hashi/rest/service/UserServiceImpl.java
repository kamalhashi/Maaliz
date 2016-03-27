package com.hashi.rest.service;

import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.Role;
import com.hashi.rest.enums.MailType;
import com.hashi.rest.exception.DuplicateUserException;

@Service("userService")
public class UserServiceImpl  implements UserService {
	private final static Logger logger = Logger
			.getLogger(UserServiceImpl.class.getName());


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource message;
	@Autowired
	ApplicationConfig applicationConfig;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * {@inheritDoc}
	 *
	 * This method creates a new user with the given Role. A check is made to see if the email already exists.
	 * <P></P>
	 *
	 */
	@Transactional
	public User createNewUser(User user) {
		User searchedForUser = userRepository.findByEmail(user.getEmail());
		if (searchedForUser != null) {
			throw new DuplicateUserException ( message.getMessage("emailExistException", null, LocaleContextHolder.getLocale())+ user.getEmail());
		}
		UserRole role = new UserRole(user, Role.ROLE_USER);
		VerificationToken token= new VerificationToken(user , MailType.EMAIL_REGISTRATION);
		user.setUserRole(role);
		user.setVerificationTokens(token);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser= userRepository.saveAndFlush(user);
		return savedUser;
	}
	
	@Transactional
	public User updateUser(User user){
		User updatedUser= userRepository.save(user);
		return updatedUser;
	}

	@Transactional(readOnly = true)
	public User findByUserId(Long id) {
		User user= userRepository.findByUserId(id);
		return user;
	}

	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		User user= userRepository.findByEmail(email);
		//only initialize when the user is not null for spring security to login
		if(user !=null)
			Hibernate.initialize(user.getUserRole());
		return user;
	}

	@Transactional
	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}

	@Override
	public int updateUserByColumnName(Long userId,
			String columnName, String data) {
		
		if(columnName.equalsIgnoreCase("password")){
			//for the password column field change
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			data= encoder.encode(data);
		}
		return userRepository.updateUserByColumnName(userId, columnName, data);	
	}

	@Override
	public boolean isPasswordSame(String password, Long userId) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, userRepository.findByUserId(userId).getPassword());
	}

	@Override
	public User createNewSocialUser(User user) {
		UserRole role = new UserRole(user, Role.ROLE_FACEBOOK);
		user.setUserRole(role);
		User savedUser = userRepository.saveAndFlush(user);
		user.setEnabled(true);
		return savedUser;
	}

}

