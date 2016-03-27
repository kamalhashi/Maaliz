package com.hashi.rest.mail;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.hashi.config.ApplicationConfig;
import com.hashi.rest.enums.MailType;
import com.hashi.rest.exception.CustomMessagingException;
import com.hashi.rest.exception.UserNotFoundException;
import com.hashi.rest.vo.EmailEnquiryVo;
import com.hashi.rest.vo.EmailReplyAdVO;
import com.hashi.rest.vo.EmailReplyJobVO;
import com.hashi.rest.vo.EmailVO;
import com.hashi.rest.vo.EmailVerificationVO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @version 1.0
 * @author: Iain Porter iain.porter@porterhead.com
 * @since 13/09/2012
 */
@Component
public class MailListener implements ApplicationListener<MailEvent>{

	private static Logger logger = Logger.getLogger(MailListener.class.getName());
	@Autowired
	private  JavaMailSender mailSender;
	@Autowired
	private SpringTemplateEngine thymeleaf;
	@Autowired
	private MessageSource message;


	@Autowired
	ApplicationConfig config;

	@Override
	public void onApplicationEvent(MailEvent event) {
		// TODO Auto-generated method stub
		switch (event.getEmailVO().getMailType()) {
		case EMAIL_REGISTRATION:
			sendRegistrationEmail((EmailVerificationVO) event.getEmailVO());
			break;
		case LOST_PASSWORD:
			sendLostPasswordEmail((EmailVerificationVO) event.getEmailVO());
			break;
		case REPLY_AD:
			sendReplyAdEmail((EmailReplyAdVO) event.getEmailVO());
			break;
		case REPLY_JOB:
			sendReplyJobEmail((EmailReplyJobVO) event.getEmailVO());
			break;
		case ENQUIRY:
			sendCustomerEnquiryEmail((EmailEnquiryVo) event.getEmailVO());
			break;
		default:
			break;
		}

	}


	public EmailVO sendVerificationEmail(final EmailVerificationVO emailVerificationModel) {
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailVerificationModel, message.getMessage("email.emailVerificationSubjectText", null, emailVerificationModel.getLocal()),
				"VerifyEmail.html", resources);
	}

	public EmailVO sendRegistrationEmail(final EmailVerificationVO emailVerificationVO) {
		System.out.println("sending email in registration..");
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailVerificationVO, message.getMessage("email.emailRegistrationSubjectText", null, emailVerificationVO.getLocal()),
				"registration_email.html", resources);
	}

	public EmailVO sendLostPasswordEmail(final EmailVerificationVO emailVerificationVO) {
		System.out.println("send lost password. email about to send");
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailVerificationVO,  message.getMessage("email.emailLostPasswordSubjectText", null, emailVerificationVO.getLocal()),
				"lost_password_email.html", resources);
	}

	public EmailVO sendReplyAdEmail(final EmailReplyAdVO emailReplyAdVO) {
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailReplyAdVO,  message.getMessage("email.emailReplyAd", null, emailReplyAdVO.getLocal()),
				"reply_ad_email.html", resources);
	}

	public EmailVO sendReplyJobEmail(final EmailReplyJobVO emailReplyJobVO) {
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailReplyJobVO,  message.getMessage("email.emailReplyJob", null, emailReplyJobVO.getLocal()),
				"reply_job_email.html", resources);
	}
	
	public EmailVO sendCustomerEnquiryEmail(final EmailEnquiryVo emailEnquiryVo) {
		Map<String, String> resources = new HashMap<String, String>();
		return sendEmail(emailEnquiryVo,  message.getMessage("email.emailCustomerEnquiry", null, emailEnquiryVo.getLocal()),
				"customer_enquiry_email.html", resources);
	}


	private EmailVO sendEmail(EmailVO emailVO, String emailSubject,
			String velocityModel,  Map<String, String> resources) {
		Context ctx = new Context(emailVO.getLocal());
		ctx.setVariable("emailVO", emailVO);
		//process the template
		String emailText = thymeleaf.process(velocityModel, ctx);
		//set up email properties and send the email
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(config.getEmailFromAddress());
			helper.setTo(emailVO.getUser().getEmail());
			helper.setSubject(emailSubject);
			helper.setText(emailText, true);
			mailSender.send(mimeMessage);

		} catch (Exception e ) {
			// TODO Auto-generated catch block
			throw new CustomMessagingException(message.getMessage("customMessagingException", null, LocaleContextHolder.getLocale()) + emailVO.getUser().getEmail());
		}       
		logger.info("sent reply ad to:" + emailVO.getUser().getEmail());
		return emailVO;
	}
	
}