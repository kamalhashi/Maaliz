package com.hashi.rest.service;


import org.springframework.web.multipart.MultipartFile;
import com.hashi.rest.domain.Profile;


/**
 * @author: Kamal Weheliye
 *
 *          Service to manage users
 */

public interface ProfileService {
	/**
	 * Create a new User
	 *
	 * @param User
	 *            User to be created
	 * @return User Created User
	 */
	public Profile createNewProfile(Profile profile, Long userId,
			MultipartFile profileCV, MultipartFile profileImage);

	/*
	 * profile page will use this method to update profile
	 */
	public Profile updateProfile(Profile profile, Long userId,
			MultipartFile profileCV, MultipartFile profileImage);


	/*
	 * details_job page will use this method to update profile user only CV not the cover letter.
	 */
	public Profile updateProfileCoverLetterAndCV(Profile profile, Long userId,
			MultipartFile profileCV, String coverLettter);



	public Profile findProfileByUserId(Long userId);

	public Profile findProfileByProfileId(Long profileId);

	public int deleteProfile(Long userId);

}
