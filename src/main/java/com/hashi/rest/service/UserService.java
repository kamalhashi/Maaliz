package com.hashi.rest.service;


import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;

/**
 * @author: Kamal Weheliye
 *
 * Service to manage users
 */

public interface  UserService {
	/**
	 * Create a new User
	 *
	 * @param User User to be created 
	 * @return User Created User
	 */
	public User  createNewUser(User user);

	/**
	 * Update User 
	 *
	 * @param User 
	 * @return updatedUser
	 */
	public User updateUser(User user);


	public User findByUserId(Long userId);

	public User findByEmail(String email);

	public void deleteUser(Long userId);
	
	public int updateUserByColumnName(Long userId, String columnName, String data);
	
	public boolean isPasswordSame(String password, Long userId);
	
	public User createNewSocialUser(User user);

}
