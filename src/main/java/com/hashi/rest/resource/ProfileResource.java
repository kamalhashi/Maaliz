package com.hashi.rest.resource;

import java.io.IOException;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashi.config.ApplicationConfig;
import com.hashi.rest.domain.Profile;
import com.hashi.rest.service.ProfileService;
import com.hashi.util.AmazonS3Service;
import com.hashi.util.MultipartToFile;



@RestController
@RequestMapping("/profile")
public class ProfileResource {
	private final static Logger logger = Logger
			.getLogger(ProfileResource.class.getName());


	private ProfileService profileService;
	private MessageSource message;	
	private AmazonS3Service amazonS3Service;
	private ApplicationConfig config;

	@Autowired
	public ProfileResource(MessageSource message,  ProfileService profileService, AmazonS3Service amazonS3Service,
			ApplicationConfig config) {
		this.profileService= profileService;
		this.message = message;
		this.amazonS3Service= amazonS3Service;
		this.config= config;
	}	


	@RequestMapping(method=RequestMethod.POST, consumes = {"multipart/form-data"}, produces="application/json")
	@PreAuthorize("permitAll")
	public ResponseEntity<Profile> createNewProfile(@RequestParam(value= "profile"  ,required=true) String  profileJson, 
			@RequestParam(value= "userId"  ,required=true) Long userId,
			@RequestPart(value= "profileCV"  ,required=false) MultipartFile profileCV,
			@RequestPart(value= "profileImage"  ,required=false) MultipartFile profileImage) 
					throws JsonParseException, JsonMappingException, IOException {


		//check the user first and then save the user 
		Profile profileObject = new ObjectMapper().readValue(profileJson, Profile.class);

		Profile createdProfile = profileService.createNewProfile(profileObject , userId, profileCV, profileImage);

		//save the files synchronously so the user can see the effect immediately
		if( profileCV != null && !profileCV.isEmpty())
			amazonS3Service.syncUploadS3File(MultipartToFile.multipartToFile(profileCV), userId, config.getBucketProfileCV());
		if( profileImage != null && !profileImage.isEmpty())
			amazonS3Service.syncUploadS3File(MultipartToFile.multipartToFile(profileImage), userId, config.getBucketProfileImage());

		return	new ResponseEntity<Profile>(createdProfile, HttpStatus.CREATED); 
	}

	@RequestMapping(value="/update",  method=RequestMethod.POST, consumes = {"multipart/form-data"}, produces="application/json")
	@PreAuthorize("permitAll")
	public ResponseEntity<Profile> updateProfile(
			@RequestParam(value= "profile"  ,required=true) String  profileJson, 
			@RequestParam(value= "userId"  ,required=true) Long userId,
			@RequestPart(value= "profileCV"  ,required=false) MultipartFile profileCV,
			@RequestPart(value= "profileImage"  ,required=false) MultipartFile profileImage) throws JsonParseException, JsonMappingException, IOException 
					 {

		Profile profileObject = new ObjectMapper().readValue(profileJson, Profile.class);

		Profile updatedProfile = profileService.updateProfile(profileObject , userId, profileCV, profileImage);
        
		//if ther's no cv file submitted then clear all cv related to user in amazon s3
		if(profileCV != null && !profileCV.isEmpty())
			amazonS3Service.syncUploadS3File(MultipartToFile.multipartToFile(profileCV), userId, config.getBucketProfileCV());
		else
			amazonS3Service.asyncDeleteS3Directory(userId, config.getBucketProfileCV());

		//if ther's no image file submitted then clear all  related to user in amazon s3
		if(profileImage != null && !profileImage.isEmpty())
			amazonS3Service.syncUploadS3File(MultipartToFile.multipartToFile(profileImage), userId, config.getBucketProfileImage());
		else
			amazonS3Service.asyncDeleteS3Directory(userId, config.getBucketProfileImage());
		
		return	new ResponseEntity<Profile>(updatedProfile, HttpStatus.CREATED); 
	}


	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public  ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
		Profile profile = profileService.findProfileByUserId(userId);
		return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}



}
