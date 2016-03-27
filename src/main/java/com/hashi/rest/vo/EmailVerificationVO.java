package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;


public class EmailVerificationVO extends EmailVO implements Serializable {

	protected  VerificationToken token;
	protected  String hostNameUrl;

	
	public EmailVerificationVO(MailType mailType,
			Locale local, User user, VerificationToken token,  String hostNameUrl) {
		super(mailType, local, user);
		this.hostNameUrl=hostNameUrl;
		this.token= token;
	}

	public VerificationToken getToken() {
		return token;
	}

	public void setToken(VerificationToken token) {
		this.token = token;
	}

	public String getHostNameUrl() {
		return hostNameUrl;
	}

	public void setHostNameUrl(String hostNameUrl) {
		this.hostNameUrl = hostNameUrl;
	}	
}