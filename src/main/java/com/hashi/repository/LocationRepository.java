package com.hashi.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hashi.rest.domain.Locations;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Long> {
	
}