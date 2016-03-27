package com.hashi.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class MailConf {
    private final static Logger logger= Logger.getLogger(MailConf.class.getName());
	@Autowired
	ApplicationConfig config;
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", config.getMailAuth());
		mailProperties.put("mail.smtp.starttls.enable", config.getMailStarttls());
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(config.getMailHost());
		mailSender.setPort(Integer.valueOf(config.getMailPort()));
		mailSender.setProtocol(config.getMailProtocol());
		mailSender.setUsername(config.getMailUsername());
		mailSender.setPassword(config.getMailPassword());
		return mailSender;
	}

	@Bean
	public ClassLoaderTemplateResolver emailTemplateResolver(){
		ClassLoaderTemplateResolver resolver = 
				new ClassLoaderTemplateResolver();
		resolver.setPrefix("templates/");
		//resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setOrder(1);
		return resolver;
	}
}