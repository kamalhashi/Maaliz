package com.hashi.rest.service;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.vo.PasswordRequest;

public interface VerificationService {

	/*
	 * verify the token
	 * 
	 * @param token: check this token whether it exist in database 
	 */
	public VerificationToken registerToken(String token);
	
	/*
	 * send Token to user email so that he can change his password
	 */
    public VerificationToken generateLostPasswordToken(String email);
    
    /*
     * Send verification
     * 
     */   
    public VerificationToken resetPassword(String token, PasswordRequest passwordRequest);
    

}
