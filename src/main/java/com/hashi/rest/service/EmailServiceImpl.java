package com.hashi.rest.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Service;
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
import com.hashi.rest.mail.MailListener;
import com.hashi.rest.resource.RegistrationController;
import com.hashi.rest.vo.EmailAdPendingVO;
import com.hashi.rest.vo.EmailEnquiryVo;
import com.hashi.rest.vo.EmailReplyAdVO;
import com.hashi.rest.vo.EmailReplyJobVO;
import com.hashi.rest.vo.EmailVO;
import com.hashi.rest.vo.EmailVerificationVO;
import com.hashi.rest.vo.PasswordRequest;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	private final static Logger logger = Logger
			.getLogger(EmailServiceImpl.class.getName());

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
	private SyncTaskExecutor syncTaskExecutor;
	@Autowired
	ApplicationConfig config;
	@Autowired
	MailListener mailListener;
	

	@Override
	public <T extends EmailVO> void sendEmailReplyAd(T t, Long userId) {
		t.setLocal(LocaleContextHolder.getLocale());
	    t.setUser(userRepository.findByUserId(userId));
		simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( t));
	}

	@Override
	public <T extends EmailVO> void sendEmailReplyJob(T t, Long userId) {
		t.setLocal(LocaleContextHolder.getLocale());
	    t.setUser(userRepository.findByUserId(userId));
	    ((EmailReplyJobVO)t).setHostNameUrl(config.getHostnameUrl());
		simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( t));
	}

	@Override
	public void sendEmailRegistration(User user) {
		VerificationToken token = verificationRepository.findByUser(user);
		simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( new EmailVerificationVO(MailType.EMAIL_REGISTRATION, LocaleContextHolder.getLocale(), user, token,config.getHostnameUrl())));		
	}

	@Override
	public  void sendEmailLostPasswordToken(User user, VerificationToken token) {
		simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( new EmailVerificationVO(MailType.LOST_PASSWORD, LocaleContextHolder.getLocale(), user, token,config.getHostnameUrl())));				
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.hashi.rest.service.EmailService#sendEmailAdPending(com.hashi.rest.vo.EmailVO, java.lang.Long)
	 * send email pending to the customer , to let them know their ad still pending
	 * 
	 */
	public  void sendEmailAdPending(User user) {
		simpleApplicationEventMulticaster.setTaskExecutor(syncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( new EmailAdPendingVO(MailType.AD_PENDING, LocaleContextHolder.getLocale(), user, config.getHostnameUrl())));				
	}

	@Override
	public <T extends EmailVO> void sendCustomerEnquiry(T t) {
		User user = new User();
		user.setEmail(config.getEmailFromAddress());
		simpleApplicationEventMulticaster.setTaskExecutor(asyncTaskExecutor);		
		simpleApplicationEventMulticaster.multicastEvent(new MailEvent( new EmailEnquiryVo(MailType.ENQUIRY, LocaleContextHolder.getLocale(), user)));						
	}

}
