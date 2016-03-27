package com.hashi.repository;


import com.hashi.rest.domain.ProfileProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileProductRepository extends JpaRepository<ProfileProduct, Long>  {
	
	public ProfileProduct findByProductProductIdAndProfileProfileId(Long productId, Long profileId);
	public Page<ProfileProduct> findByProductProductId(Long productId, Pageable pageable);
}