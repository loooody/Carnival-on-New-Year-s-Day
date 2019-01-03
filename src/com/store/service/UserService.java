package com.store.service;

import java.sql.SQLException;

import com.store.entity.User;

public interface UserService {

	void userRegist(User user);

	void userActive(String code);

	User userLogin(User user);
	
}
