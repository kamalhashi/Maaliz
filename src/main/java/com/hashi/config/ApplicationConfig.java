package com.hashi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: porter Date: 17/05/2012 Time: 19:07
 */
@Configuration
@PropertySource({ "classpath:/application.properties" })
public class ApplicationConfig {
	@Autowired
	protected Environment environment;

	/*
	 * Fetching configuration parameters from the applicatioin.properties
	 */

	// Local Database properties
	private final static String LOCAL_DB_URL = "local.db.url";
	private final static String LOCAL_DB_DRIVER = "local.db.driver";
	private final static String LOCAL_DB_USERNAME = "local.db.username";
	private final static String LOCAL_DB_PASSWORD = "local.db.password";

	// Cloud Database properties

	private final static String CLOUD_DB_URL = "cloud.db.url";
	private final static String CLOUD_DB_DRIVER = "cloud.db.driver";
	private final static String CLOUD_DB_USERNAME = "cloud.db.username";
	private final static String CLOUD_DB_PASSWORD = "cloud.db.password";

	private final static String PACKAGE_TO_SCAN = "entitymanager.packagesToScan";

	private final static String MAIL_PROTOCOL = "mail.protocol";
	private final static String MAIL_HOST = "mail.host";
	private final static String MAIL_PORT = "mail.port";
	private final static String MAIL_AUTH = "mail.smtp.auth";
	private final static String MAIL_STARTTLS = "mail.smtp.starttls.enable";
	private final static String MAIL_FROM = "mail.from";
	private final static String MAIL_USERNAME = "mail.username";
	private final static String MAIL_PASSWORD = "mail.password";
	private final static String HOSTNAME_URL = "hostName.Url";

	private final static String EMAIL_FROM_ADDRESS = "mail.from";

	private final static String FACEBOOK_CLIENT_ID = "spring.social.facebook.appId";
	private final static String FACEBOOK_CLIENT_SECRET = "spring.social.facebook.appSecret";

	private final static String ENCRYPT_PASSWORD = "security.encryptPassword";
	private final static String ENCRYPT_SALT = "security.encryptSalt";

	private final static String S3_ACCESSKEY = "s3AccessKey";
	private final static String S3_SECRETKEY = "s3SecretKey";
	private final static String BUCKET_PRODUCT_IMAGES= "s3BucketProductImages";
	private final static String BUCKET_PROFILE_CV="s3BucketProfileCV";
	private final static String BUCKET_PROFILE_IMAGE="s3BucketProfileImage";
	private final static String BUCKET_PRODUCT_LOGO= "s3BucketProductLogo";


	public String getMailProtocol() {
		return environment.getProperty(MAIL_PROTOCOL);
	}

	public String getMailHost() {
		return environment.getProperty(MAIL_HOST);
	}

	public String getMailPort() {
		return environment.getProperty(MAIL_PORT);
	}

	public String getMailAuth() {
		return environment.getProperty(MAIL_AUTH);
	}

	public String getMailStarttls() {
		return environment.getProperty(MAIL_STARTTLS);
	}

	public String getMailFrom() {
		return environment.getProperty(MAIL_FROM);
	}

	public String getMailUsername() {
		return environment.getProperty(MAIL_USERNAME);
	}

	public String getMailPassword() {
		return environment.getProperty(MAIL_PASSWORD);
	}

	// Local Database getters
	public String getLocalDbDriver() {
		return environment.getProperty(LOCAL_DB_DRIVER);
	}

	public String getLocalDbUrl() {
		return environment.getProperty(LOCAL_DB_URL);
	}

	public String getLocalDbUsername() {
		return environment.getProperty(LOCAL_DB_USERNAME);
	}

	public String getLocalDbPassword() {
		return environment.getProperty(LOCAL_DB_PASSWORD);
	}

	// Cloud Database getters

	public String getCloudDbDriver() {
		return environment.getProperty(CLOUD_DB_DRIVER);
	}

	public String getCloudDbUrl() {
		return environment.getProperty(CLOUD_DB_URL);
	}

	public String getCloudDbUsername() {
		return environment.getProperty(CLOUD_DB_USERNAME);
	}

	public String getCloudDbPassword() {
		return environment.getProperty(CLOUD_DB_PASSWORD);
	}

	// Email getters

	public String getEmailFromAddress() {
		return environment.getProperty(EMAIL_FROM_ADDRESS);
	}

	public String getHostnameUrl() {
		return environment.getProperty(HOSTNAME_URL);
	}

	public String getFacebookClientId() {
		return environment.getProperty(FACEBOOK_CLIENT_ID);
	}

	public String getFacebookClientSecret() {
		return environment.getProperty(FACEBOOK_CLIENT_SECRET);
	}

	public String getEncryptPassword() {
		return environment.getProperty(ENCRYPT_PASSWORD);
	}

	public String getEncryptSalt() {
		return environment.getProperty(ENCRYPT_SALT);
	}

	public String getS3Accesskey() {
		return environment.getProperty(S3_ACCESSKEY);
	}

	public String getS3Secretkey() {
		return environment.getProperty(S3_SECRETKEY);
	}

	
	public  String getPackageToScan() {
		return environment.getProperty(PACKAGE_TO_SCAN);
	}

	public String getBucketProductImages() {
		return environment.getProperty(BUCKET_PRODUCT_IMAGES);
	}

	public  String getBucketProfileCV() {
		return environment.getProperty(BUCKET_PROFILE_CV);
	}

	public  String getBucketProfileImage() {
		return environment.getProperty(BUCKET_PROFILE_IMAGE);
	}

	public String getBucketProductLogo() {
		return environment.getProperty(BUCKET_PRODUCT_LOGO);
	}


}
