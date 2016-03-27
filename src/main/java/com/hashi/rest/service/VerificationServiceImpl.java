package com.hashi.rest.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.UserRepository;
import com.hashi.repository.VerificationTokenRepository;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;
import com.hashi.rest.exception.AlreadyVerifiedException;
import com.hashi.rest.exception.EmailNotFoundException;
import com.hashi.rest.exception.TokenHasExpiredException;
import com.hashi.rest.exception.VerificationNotFoundException;
import com.hashi.rest.mail.MailEvent;
import com.hashi.rest.resource.RegistrationController;
import com.hashi.rest.vo.EmailVerificationVO;
import com.hashi.rest.vo.PasswordRequest;

@Service("verificationService")
public class VerificationServiceImpl implements VerificationService {
	private final static Logger logger = Logger
			.getLogger(VerificationServiceImpl.class.getName());

	@Autowired
	private VerificationTokenRepository verificationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageSource message;
	@Autowired
	private SimpleApplicationEventMulticaster simpleApplicationEventMulticaster;
	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;
	@Autowired
	ApplicationConfig config;
	@Autowired
	private PasswordEncoder passwordEncoder;


	public VerificationToken registerToken(String token) {
		VerificationToken verifyToken = verificationRepository.findByToken(token);

		if(verifyToken == null)
			throw new VerificationNotFoundException (message.getMessage("verificationNotFoundException", null, LocaleContextHolder.getLocale()));
		else if(verifyToken.isVerified() || verifyToken.getUser().isEnabled())
			throw new AlreadyVerifiedException(message.getMessage("alreadyVerifiedException", null, LocaleContextHolder.getLocale())+
					verifyToken.getUser().getEmail());
		else if(verifyToken.hasExpired())
			throw new TokenHasExpiredException(message.getMessage("tokenHasExpiredException", null, LocaleContextHolder.getLocale())+
					verifyToken.getUser().getEmail());

		verifyToken.setVerified(true);
		verifyToken.getUser().setEnabled(true);
		verificationRepository.save(verifyToken);
		return verifyToken;
	}

	

	/**
	 * generate token if user found otherwise do nothing
	 *
	 * @param lostPasswordRequest
	 * @return  a token or null if user not found
	 */
	@Transactional
	public VerificationToken generateLostPasswordToken(String email) {
		VerificationToken token = null;
		User user= userRepository.findByEmail(email);
		if(user ==null){
			throw new EmailNotFoundException(message.getMessage("emailNotFoundException", null, LocaleContextHolder.getLocale())+
					email);

		}
		else if (user != null) {
			token = user.getActiveLostPasswordToken();
			if (token == null) {
				token = new VerificationToken(user, MailType.LOST_PASSWORD);
				user.setVerificationTokens(token);
				userRepository.save(user);
			}
		}
		return token;
	}

	@Transactional
	public VerificationToken resetPassword(String tokenParam, PasswordRequest passwordRequest) {
		logger.log(Level.INFO, "resetPassword {0} ", tokenParam);

		VerificationToken token = verificationRepository.findByToken(tokenParam);

		if (token.isVerified()) {
			throw new AlreadyVerifiedException(message.getMessage("alreadyVerifiedException", null, LocaleContextHolder.getLocale())+
					tokenParam);       
		}
		else if(token.hasExpired())
			throw new TokenHasExpiredException(message.getMessage("tokenHasExpiredException", null, LocaleContextHolder.getLocale())+
					token.getUser().getEmail());
		
		token.setVerified(true);
		User user = token.getUser();
		user.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        //set user to verified if not already
		user.setEnabled(true);
		userRepository.save(user);
		return token;
	}

}
