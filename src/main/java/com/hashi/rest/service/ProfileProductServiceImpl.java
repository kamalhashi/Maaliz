package com.hashi.rest.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.config.ApplicationConfig;
import com.hashi.repository.ProfileProductRepository;
import com.hashi.repository.ProductRepository;
import com.hashi.repository.ProfileRepository;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.Product;
import com.hashi.rest.domain.Profile;
import com.hashi.rest.domain.ProfileProduct;


@Service("applicantService")
public class ProfileProductServiceImpl  implements ProfileProductService {
	private final static Logger logger = Logger
			.getLogger(ProfileProductServiceImpl.class.getName());


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileProductRepository profileProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProfileRepository profileRepository;


	@Autowired
	private MessageSource message;
	@Autowired
	ApplicationConfig config;
	
	private static final int NUMBER_OF_PRODUCTS_PER_PAGE = 10;



	@Override
	@Transactional
	public ProfileProduct save(Long userId, Long profileId, Long productId) {
		System.out.println("userId:" + userId);
		

        Profile profile= profileRepository.findOne(profileId);
        System.out.println("profileId:" + profile.getProfileId());
        Product product= productRepository.findOne(productId);

		//System.out.println("productId:" + product.getProductId());

		ProfileProduct profileProduct = new ProfileProduct();
		profileProduct.setProduct(product);
		profileProduct.setProfile(profile);
		return profileProductRepository.saveAndFlush(profileProduct);
	}


	@Override
	public ProfileProduct isApplicantExist(Long productId , Long userId, Long profileId) {
		System.out.println(userId+ ":" + productId+ ":" + ":"+ profileId);
		ProfileProduct profileProduct = profileProductRepository.findByProductProductIdAndProfileProfileId(productId,profileId);
		return  profileProduct;
	}


	@Override
	public Page<ProfileProduct> getProductProfilesByProductId(Long productId, String sortColumn, String sortDirection,  int pageIndex) {
		return profileProductRepository.findByProductProductId(productId,  constructPageSpecification(pageIndex, sortColumn, sortDirection));
	}
	
	
	/**
	 * Returns a new object which specifies the the wanted result page.
	 * 
	 * @param pageIndex
	 *            The index of the wanted result page
	 * @return
	 */
	private Pageable constructPageSpecification(int pageIndex, String sortColumn, String sortDirection) {
		Pageable pageSpecification = new PageRequest(pageIndex,
				NUMBER_OF_PRODUCTS_PER_PAGE, Sort.Direction.fromString(sortDirection), sortColumn);
		return pageSpecification;
	}
}

