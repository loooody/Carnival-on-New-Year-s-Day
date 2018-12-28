package com.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.dao.UserDao;
import com.store.entity.User;

@Component
public class UserServiceImpl {

	@Autowired
	private UserDao userDao;

	public String getUser(String str) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(str);
		return user.toString();
	}
}
