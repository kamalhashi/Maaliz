package com.hashi.rest.mail;


import org.springframework.context.ApplicationEvent;

import com.hashi.rest.vo.EmailVO;
import com.hashi.rest.vo.EmailVerificationVO;

public class MailEvent extends ApplicationEvent {
    private final EmailVO emailVO;
 
    public MailEvent(EmailVO emailVO) {
        super(emailVO);
        this.emailVO = emailVO;
    }

	public EmailVO getEmailVO() {
		return emailVO;
	}
     
}