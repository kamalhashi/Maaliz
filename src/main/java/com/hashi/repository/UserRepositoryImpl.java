package com.hashi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hashi.rest.domain.User;

public class UserRepositoryImpl   implements UserRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;
		

	@Override
	@Modifying
	@Transactional
	public int updateUserByColumnName(Long userId, String columnName,
			String data) {
		Query query = entityManager.createQuery("UPDATE User u SET u." + columnName + " = :data WHERE u.userId = :userId" );
		query.setParameter("data", data);
		query.setParameter("userId", userId);
		
		return query.executeUpdate();
	}
	
	@Transactional
    @CacheEvict(value = "listRootCategoriesSomali", allEntries=true)    
	public void refreshSomaliCategories(){
		System.out.println("Refresh Somali Cateogires");
	}
	
	@Transactional
    @CacheEvict(value = "listRootCategoriesEnglish", allEntries=true)    
	public void refreshEnglishCategories(){
		System.out.println("Refresh English Cateogires");
	}
	
	
}
