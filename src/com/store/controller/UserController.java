package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.entity.User;
import com.store.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/user")
	public String user_list(){
		String user  = userService.getUser("aaa");
		System.out.println(user);
		return "index";
	}
	//test
	
	//test2
}
