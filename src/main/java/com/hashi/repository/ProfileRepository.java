package com.hashi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hashi.rest.domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>  {

	public Profile findProfileByUserUserId(Long userId);
}