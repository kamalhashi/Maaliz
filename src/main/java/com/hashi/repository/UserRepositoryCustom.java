package com.hashi.repository;

import com.hashi.rest.domain.User;

public interface UserRepositoryCustom {
	int updateUserByColumnName(Long userId,String columnName, String data);
	public void refreshEnglishCategories();
	public void refreshSomaliCategories();
}
