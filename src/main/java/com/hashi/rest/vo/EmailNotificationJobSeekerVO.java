package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;
import com.hashi.rest.domain.User;
import com.hashi.rest.enums.MailType;


public class EmailNotificationJobSeekerVO extends EmailVO implements Serializable {
	
	private String productTitle;
	protected  String hostNameUrl;

	public EmailNotificationJobSeekerVO(){
		super();
	}
	public EmailNotificationJobSeekerVO(MailType mailType, Locale local, User user, String productTitle) {
		super(mailType, local, user);
		this.productTitle= productTitle;
	}
	public String getHostNameUrl() {
		return hostNameUrl;
	}
	public void setHostNameUrl(String hostNameUrl) {
		this.hostNameUrl = hostNameUrl;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

}