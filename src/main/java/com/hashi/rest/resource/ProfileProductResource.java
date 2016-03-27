package com.hashi.rest.resource;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashi.config.ApplicationConfig;
import com.hashi.rest.domain.ProfileProduct;
import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.Profile;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.exception.ApplicantExistException;
import com.hashi.rest.exception.ProductCreationException;
import com.hashi.rest.service.CategoryService;
import com.hashi.rest.service.EmailService;
import com.hashi.rest.service.ProductService;
import com.hashi.rest.service.ProfileProductService;
import com.hashi.rest.service.ProfileService;
import com.hashi.rest.vo.EmailReplyJobVO;
import com.hashi.util.AmazonS3Service;
import com.hashi.util.MultipartToFile;
import com.hashi.util.View;



@RestController
@RequestMapping("/applicants")
public class ProfileProductResource {
	private final static Logger logger = Logger
			.getLogger(ProfileProductResource.class.getName());


	private ProductService productService;
	private CategoryService categoryService;
	private ProfileService profileService;
	private EmailService emailService;
	private ProfileProductService profileProductService;
	private MessageSource message;
	private AmazonS3Service amazonS3Service;
	private ApplicationConfig config;

	

	@Autowired
	public ProfileProductResource(MessageSource message,  ProductService productService, CategoryService categoryService,
			ProfileProductService profileProductService, EmailService emailService, ProfileService profileService,
			ApplicationConfig config,  AmazonS3Service amazonS3Service) {
		this.productService= productService;
		this.profileProductService= profileProductService;
		this.message = message;
		this.categoryService= categoryService;
		this.profileService= profileService;
		this.emailService= emailService;
		this.config= config;
		this.amazonS3Service= amazonS3Service;
	}	

	@RequestMapping(value="/job/{productId}", method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.High.class)
	public  ResponseEntity<Page<ProfileProduct>> getJobApplicants(@PathVariable Long productId,
			@RequestParam(value= "pageIndex"  ,required=false) Integer pageIndex,
			@RequestParam(value= "sortColumn"  ,required=false) String sortColumn,
			@RequestParam(value= "sortDirection"  ,required=false) String sortDirection) throws IOException {
		System.out.println("pageIndex" + pageIndex);
		System.out.println("sortColumn" + sortColumn);
		System.out.println("sortDirection" + sortDirection);

		Page<ProfileProduct> profileProductList = profileProductService.getProductProfilesByProductId(productId, sortColumn, sortDirection, pageIndex);
		return new ResponseEntity<Page<ProfileProduct>>(profileProductList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/language/{language}/user/{userId}", method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public  ResponseEntity<List<Product>> hasUserPostedJobs(@PathVariable("language") LanguageType language, @PathVariable Long userId) {
		List<Product> jobProducts= new ArrayList<Product>();
		
		List<Product> userProducts = productService.findProductsByUserId(userId);
		for(Product product: userProducts){
			if(categoryService.isCategoryBelongsJob(language, product.getCategoriesEntity().getCategoryId())){	
				jobProducts.add(product);
			}
		}		
		return new ResponseEntity<List<Product>>(jobProducts, HttpStatus.OK);
	}
	
	
	
	/*
	 * this method is a little bit tricky, because we dealing with two userId's{jobseekerUserId, empleyerUserId}
	 * also this method will check whether theirs a profile created for the jobseeker, if ther's no profile created 
	 * profile and send email, else escape creating profile and update the profile and send email notification to 
	 * the employer
	 */
	@RequestMapping(value="/reply/job",  method=RequestMethod.POST, consumes = {"multipart/form-data"}, produces="application/json")
	@PreAuthorize("permitAll")
	public ResponseEntity<ProfileProduct> saveApplicant(
			@RequestParam(value= "emailReplyJobVO"  ,required=true) String  emailReplyJobVO, 
			@RequestParam(value= "employerUserId"  ,required=true) Long employerUserId,
			@RequestParam(value= "jobSeekerUserId"  ,required=true) Long jobSeekerUserId,
			@RequestParam(value= "profileId"  ,required=true) Long profileId,
			@RequestPart(value= "fileCV"  ,required=false) MultipartFile fileCV) throws JsonParseException, JsonMappingException, IOException{		

		ProfileProduct profileProduct=null;
		EmailReplyJobVO replyJob = new ObjectMapper().readValue(emailReplyJobVO, EmailReplyJobVO.class);

		/*check whether the applicant has profile , if profileId is zero then applicants needs new profile.
		 * create a profile for the new applicant who just applied his first job.  
		 * 
		 */
		if(profileId==0){
			Profile profile= new Profile();
			//the cover letter can be changes or edited, so save set the cover letter to the latest input
			profile.setCoverLetter(replyJob.getCoverLetter());
			
			//create new profile for the jobSeeker
			Long newProfileId= profileService.createNewProfile(profile, jobSeekerUserId, fileCV, null).getProfileId();
			
			//set the new profile created in the replyJob object {**needed in the email template**}
			replyJob.setProfile(profileService.findProfileByProfileId(newProfileId));
			
			profileProduct= profileProductService.save(jobSeekerUserId, newProfileId, replyJob.getProductId());
			
			//save the file if it exist
			if( fileCV != null && !fileCV.isEmpty()){
				 amazonS3Service.asyncUploadS3File(MultipartToFile.multipartToFile(fileCV) , jobSeekerUserId, config.getBucketProfileCV());
			}
			
			//send email when the process finishes to the emplyer
			emailService.sendEmailReplyJob(replyJob, employerUserId);
		}else{ //there's another case where the user has profile and applied the job 
			Profile profile= profileService.findProfileByProfileId(profileId);
			/*if the user has applied the job then don't allow the same user to apply the job multiple times.
			 * in here we check the JOB_USER database if the userId has applied this job
			 *
			 */
			 profileProduct = profileProductService.isApplicantExist(replyJob.getProductId(), profile.getUser().getUserId(), profile.getProfileId());
			 if( profileProduct !=null ){
					throw new ApplicantExistException(message.getMessage("applicantExistException", null, LocaleContextHolder.getLocale()) + profileProduct.getCreated());

			 }else{
				 profileService.updateProfileCoverLetterAndCV(profile, jobSeekerUserId, fileCV, replyJob.getCoverLetter());
				 replyJob.setProfile(profileService.findProfileByProfileId(profileId));
				 profileProduct= profileProductService.save(jobSeekerUserId, profileId, replyJob.getProductId());
				 
				 //upload the file if its not empty, else delete the folder
				 if( fileCV != null && !fileCV.isEmpty()){ 
					 amazonS3Service.asyncUploadS3File(MultipartToFile.multipartToFile(fileCV) , jobSeekerUserId, config.getBucketProfileCV());
				 }else{
					 amazonS3Service.asyncDeleteS3Directory(jobSeekerUserId, config.getBucketProfileCV());
				 }
				 emailService.sendEmailReplyJob(replyJob, employerUserId);
			 }
		}
		return	new ResponseEntity<ProfileProduct>(profileProduct, HttpStatus.CREATED); 
	}

}
