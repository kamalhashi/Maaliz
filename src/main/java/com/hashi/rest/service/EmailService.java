package com.hashi.rest.service;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.vo.EmailReplyAdVO;
import com.hashi.rest.vo.EmailVO;
import com.hashi.rest.vo.EmailVerificationVO;
import com.hashi.rest.vo.PasswordRequest;

public interface EmailService {

	/*
	 * verify the token
	 * 
	 * @param token: check this token whether it exist in database 
	 */
	/*
	 * after user is registered send Token to user email,
	 * to make verify his email 
	 */
	public void sendEmailRegistration(User user);
	/*
	 * send Token to user email so that he can change his password
	 */
    public void sendEmailLostPasswordToken( User user , VerificationToken token);
   
    /*
     * 
     * Send reply email to the user who posted it.
     */
    public <T extends EmailVO> void  sendEmailReplyAd(T   t, Long userId);
    
    /*
     * 
     * Send reply email to job.
     */
    public <T extends EmailVO> void  sendEmailReplyJob(T   t, Long userId);
    
    /**
     * 
     * send enquiry email 
     */
    public <T extends EmailVO> void  sendCustomerEnquiry(T   t);


}
