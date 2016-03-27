package com.hashi.repository;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hashi.rest.domain.CityEnglish;
import com.hashi.rest.domain.CitySomali;

@Repository

public interface CitySomaliRepository extends JpaRepository<CitySomali, Long> {
	
	@Cacheable("findByCityIdSomali")
	public CitySomali findByCityId(Long cityId);
	
	@Cacheable(value = "listCitySomali")
	List<CitySomali> findAll();
	
	
}