package com.hashi.repository;
import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hashi.rest.domain.CityEnglish;
import com.hashi.rest.domain.CountryEnglish;

@Repository
@CacheConfig(cacheNames="listCityEnglish")

public interface CityEnglishRepository extends JpaRepository<CityEnglish, Long> {
	
	@Cacheable("findByCityIdEnglish")
	public CityEnglish findByCityId(Long cityId);
	
	@Cacheable(value = "listCityEnglish")
	List<CityEnglish> findAll();
	
}