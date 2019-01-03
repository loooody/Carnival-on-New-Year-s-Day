package com.store.dao;


import com.store.entity.User;

public interface UserDao {

	void userRegist(User user);
//
//	User userActive(String code);
//
	void updateUser(User user);
	
	void updateUserPwd(String uid,String newPwd);

	User userLogin(User user);


} 

