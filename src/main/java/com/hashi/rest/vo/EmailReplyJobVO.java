package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.web.multipart.MultipartFile;

import com.hashi.rest.domain.Profile;
import com.hashi.rest.domain.User;
import com.hashi.rest.enums.MailType;


public class EmailReplyJobVO extends EmailVO implements Serializable {

	
	private String productTitle;
	private Long productId;
	private Profile profile;
	private String coverLetter;
	protected  String hostNameUrl;

	public EmailReplyJobVO(){
		super();
	}
	public EmailReplyJobVO(MailType mailType, Locale local, User user) {
		super(mailType, local, user);
	}
	
	public Profile getProfile() {
		return profile;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getHostNameUrl() {
		return hostNameUrl;
	}
	public void setHostNameUrl(String hostNameUrl) {
		this.hostNameUrl = hostNameUrl;
	}
	
}