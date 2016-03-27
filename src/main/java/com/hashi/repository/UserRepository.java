package com.hashi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.rest.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom  {
	public User findByEmail(String email);
	public User findByUserId(Long userId);
	@Modifying
	@Query("UPDATE User u SET u.firstname = :firstname WHERE u.userId = :userId")
	@Transactional
	int updateAddress(@Param("userId") Long userId, @Param("firstname") String firstname);
}