package com.hashi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.rest.domain.User;
import com.hashi.rest.domain.VerificationToken;
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	public VerificationToken findByToken(String token);
	public VerificationToken findByUser(User user);
}
