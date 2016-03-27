package com.hashi.rest.service;


import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.ProfileRepository;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.Profile;


@Service("profileService")
public class ProfileServiceImpl  implements ProfileService {
	private final static Logger logger = Logger
			.getLogger(ProfileServiceImpl.class.getName());


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;


	@Autowired
	private MessageSource message;
	@Autowired
	ApplicationConfig config;

	private final static String FOLDER_SUFFIX = "/";

	@Override
	@Transactional
	public Profile createNewProfile(Profile profile, Long userId, MultipartFile profileCV, MultipartFile profileImage) {
		profile.setUser(userRepository.findByUserId(userId));
		// TODO Auto-generated method stub
		
		if(profileCV != null && !profileCV.isEmpty())
			profile.setCvName(profileCV.getOriginalFilename());

		if(profileImage != null && !profileImage.isEmpty())
			profile.setImageName(profileImage.getOriginalFilename());

		return  profileRepository.saveAndFlush(profile);		
	}

	@Override
	@Transactional(readOnly = true)
	public Profile findProfileByUserId(Long userId) {
		return profileRepository.findProfileByUserUserId(userId);
	}

	@Override
	@Transactional
	public Profile updateProfile(Profile profile, Long userId, MultipartFile profileCV, MultipartFile profileImage) {
		profile.setUser(userRepository.findByUserId(userId));
		if( profileCV != null && !profileCV.isEmpty()){
			profile.setCvName(profileCV.getOriginalFilename());
		}else{
			profile.setCvName(null);
		}
		if( profileImage != null && !profileImage.isEmpty()){
			profile.setImageName(profileImage.getOriginalFilename());
		}
		else{
			profile.setImageName(null);	
		}

		return profileRepository.save(profile);
	}
	
	
	
	
	@Override
	@Transactional
	public Profile updateProfileCoverLetterAndCV(Profile profile, Long userId, MultipartFile profileCV, String coverLetter) {
		profile.setUser(userRepository.findByUserId(userId));
		profile.setCoverLetter(coverLetter);
		if( profileCV != null && !profileCV.isEmpty()){
			profile.setCvName(profileCV.getOriginalFilename());			
		}else{
			profile.setCvName(null);
		}
		return profileRepository.save(profile);
	}




	@Override
	@Transactional
	public int deleteProfile(Long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Profile findProfileByProfileId(Long profileId) {
		return profileRepository.findOne(profileId);
	}



}

