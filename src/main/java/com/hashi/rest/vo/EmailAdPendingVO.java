package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;


public class EmailAdPendingVO extends EmailVO implements Serializable {

	protected  String hostNameUrl;

	public EmailAdPendingVO(){
		super();
	}
	
	public EmailAdPendingVO(MailType mailType,
			Locale local, User user,  String hostNameUrl) {
		super(mailType, local, user);
		this.hostNameUrl=hostNameUrl;

	}

	public String getHostNameUrl() {
		return hostNameUrl;
	}

	public void setHostNameUrl(String hostNameUrl) {
		this.hostNameUrl = hostNameUrl;
	}	
}