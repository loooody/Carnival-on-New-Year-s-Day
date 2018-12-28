package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.store.dao.UserDao;
import com.store.entity.User;

@Service
public interface UserService {
	
	User getUser(String str);
	
}
