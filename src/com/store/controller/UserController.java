package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.User;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("loginUI")
	public ModelAndView loginUI() {
		return new ModelAndView("jsp/login");
	}
	
	@RequestMapping("registUI")
	public ModelAndView registUI() {
		return new ModelAndView("jsp/register");
	}
	
	@RequestMapping("userLogin")
	public ModelAndView userLogin(String username,String password,HttpServletRequest request) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		User user02 = null;
		/*
		 * 登录有三种情况
		 * 1-登录成功
		 * 2-登录失败
		 * 登录失败又分三种：1-账号不存在，2-密码错误，3-用户未激活
		 * 第一种情况可以直接使用ajax异步校验
		 * 
		 * 获取到登录状态可以使用数字表示状态（1,2,3分别表示成功，密码错误，未激活）
		 * 也可以使用异常的形式进行处理。
		 */
		try {
			//select * from user where username=? and password=?
			user02 = userService.userLogin(user);
			//	用户登录成功，将用户信息放入session中
			request.getSession().setAttribute("loginUser", user02);
			//	重定向到主页
			return new ModelAndView("jsp/index");
		} catch (Exception e) {
			//	用户登录失败
			String msg = e.getMessage();
			System.out.println(msg);
			//	向request中放入失败的信息
			request.setAttribute("msg", msg);
			return new ModelAndView("jsp/login");
		}
	}
	
	
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		//清除session
		request.getSession().invalidate();
		//	重定向到首页
		return new ModelAndView("index");
	}
	
}
