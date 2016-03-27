package com.hashi.rest.vo;

import java.io.Serializable;
import java.util.Locale;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;


public class EmailReplyAdVO extends EmailVO implements Serializable {

	
	private  String replierEmail;
	private  String replierMessage;
	private String replierName;
	private  String replierTelephone;
	private String mailTo;
	private String productTitle;
	private Integer productId;
	
	
	public EmailReplyAdVO(){
		super();
	}
	public EmailReplyAdVO(MailType mailType, Locale local, User user) {
		super(mailType, local, user);
		// TODO Auto-generated constructor stub
	}

	public String getReplierEmail() {
		return replierEmail;
	}
	public String getReplierMessage() {
		return replierMessage;
	}
	
	public String getMailTo() {
		return mailTo;
	}
	public void setReplierEmail(String replierEmail) {
		this.replierEmail = replierEmail;
	}
	public void setReplierMessage(String replierMessage) {
		this.replierMessage = replierMessage;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getReplierName() {
		return replierName;
	}
	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}
	public String getReplierTelephone() {
		return replierTelephone;
	}
	public void setReplierTelephone(String replierTelephone) {
		this.replierTelephone = replierTelephone;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}