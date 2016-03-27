package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;


public class EmailEnquiryVo extends EmailVO implements Serializable {


	private  String name;
	private  String email;
	private String telephone;
	private  String natureEnquiry;

	public EmailEnquiryVo(){
		super();
	}

	public EmailEnquiryVo(MailType mailType, Locale local, User user) {
		super(mailType, local, user);
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getNatureEnquiry() {
		return natureEnquiry;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setNatureEnquiry(String natureEnquiry) {
		this.natureEnquiry = natureEnquiry;
	}

}