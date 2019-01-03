package com.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.utils.MD5Utils;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,String username,String password) {
		password = MD5Utils.md5(password);
		if(username.equals("admin") && password.equals("21232f297a57a5a743894a0e4a801fc3")) {
			return new ModelAndView("/admin/home");
		}else {
			String msg = "密码错误";
			return new ModelAndView("/admin/index","msg",msg);
		}
		
	}
}

