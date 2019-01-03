package com.store.dao;


import com.store.entity.User;

public interface UserDao {

	void userRegist(User user);
//
	void userActive(String code);
//
	void updateUser(User user);
    
	User userLogin(User user);

} 

