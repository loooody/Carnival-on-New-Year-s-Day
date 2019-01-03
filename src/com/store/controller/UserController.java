package com.store.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.store.utils.DateTransfrormUtils;
import com.store.utils.MD5Utils;
import com.store.utils.UUIDUtils;
import com.store.utils.MD5Utils;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	
	UserServiceImpl userService;

	MD5Utils MD5;
	UUIDUtils UID;

	@RequestMapping("loginUI")
	public ModelAndView loginUI() {
		return new ModelAndView("jsp/login");
	}

	@RequestMapping("registUI")
	public ModelAndView registUI() {
		return new ModelAndView("jsp/register");
	}

	@RequestMapping("userLogin")
	public ModelAndView userLogin(String username, String password, HttpServletRequest request) {
		User user = new User();

		user.setUsername(username);
		//password md5加密
		String PwdMd5=MD5.md5(password);
		user.setPassword(PwdMd5);

		User user02 = null;
		/*
		 * 登录有三种情况 1-登录成功 2-登录失败 登录失败又分三种：1-账号不存在，2-密码错误，3-用户未激活
		 * 第一种情况可以直接使用ajax异步校验
		 * 
		 * 获取到登录状态可以使用数字表示状态（1,2,3分别表示成功，密码错误，未激活） 也可以使用异常的形式进行处理。
		 */
		try {
			// select * from user where username=? and password=?
			user02 = userService.userLogin(user);
			// 用户登录成功，将用户信息放入session中
			request.getSession().setAttribute("loginUser", user02);

			return new ModelAndView("index");
		} catch (Exception e) {
			// 用户登录失败
			String msg = e.getMessage();
			System.out.println(msg);
			// 向request中放入失败的信息
			request.setAttribute("msg", msg);
			return new ModelAndView("jsp/login");
		}
	}

	@RequestMapping("userRegister")
	public ModelAndView register(String username, String pwd, String ConfirmPwd, String email, String name, String sex,
			String birthday, String telephone, HttpServletRequest request) {
		Date DateBirthday = new Date();
		// String 的生日类型转换为Date型
		if(birthday.equals("")==false){
		DateTransfrormUtils dateTranf = new DateTransfrormUtils();
		DateBirthday = dateTranf.String2Date(birthday);
		}
		// 密码MD5加密
		String PwdMd5 = MD5.md5(pwd);
		// 获得uid
		String uid = UID.getId();
		// 将用户设置为激活状态
		int state = 0;
		//添加激活码
		String code = UID.getCode();
		// 创建用户
		User user = new User(uid, username, PwdMd5, name, email, telephone, DateBirthday, sex, state, code);
		// 将用户存入数据库
		userService.userRegist(user);
		request.setAttribute("msg", "注册成功,快去邮箱激活吧");
		return new ModelAndView("jsp/login");
	}
	//邮箱激活
	@RequestMapping("activateUI")
	public ModelAndView activate(String mailcode,HttpServletRequest request){
		
		System.out.println(mailcode);
		if(mailcode.equals(null)==false){
			userService.userActive(mailcode);			
		}else{
			request.setAttribute("msg", "激活失败,无激活码");
		}
		return new ModelAndView("jsp/activate");
		
	}
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request) {
		// 清除session
		request.getSession().invalidate();
		// 重定向到首页
		return new ModelAndView("index");
	}

	
	@RequestMapping("userinfo")
	public ModelAndView userInfo(HttpServletRequest request) {
		//User user = (User)request.getSession().getAttribute("loginUser");
		//System.out.print(user);
		return new ModelAndView("jsp/user_info");
	}
	
	@RequestMapping("changePwdUI")
	public ModelAndView jumpToChangePwd(HttpServletRequest request) {
		return new ModelAndView("jsp/changePwd");
	}
	
	@RequestMapping("changeInfoUI")
	public ModelAndView jumpToChangeInfo(HttpServletRequest request) {
		return new ModelAndView("jsp/changeInfo");
	}
	
	@RequestMapping("changePwd")
	public String changePwd(String oldPwd,String newPwd,HttpServletRequest request) {
		String passwordString = MD5.md5(oldPwd);
		System.out.println(passwordString);
		User user = (User)request.getSession().getAttribute("loginUser");
		String uid = user.getUid();
		if(!passwordString.equals(user.getPassword())) {
			String msg = "原始密码错误！";
			request.getSession().setAttribute("msg", msg);
			System.out.println(request.getSession().getAttribute("msg"));
			return "redirect:changePwdUI";
		}
		//System.out.println(uid);
		newPwd = MD5.md5(newPwd);   //对密码进行hash
		userService.updateUserPwd(uid, newPwd);
		return "redirect:userinfo";
	}

	
	@RequestMapping("changeInfo")
	public String changeInfo(String nickname,String email,String sex,String birthday,String telephone,HttpServletRequest request) throws ParseException {
//		if(birthday==null)
//			System.out.println(nickname+email+sex+birthday);
//		else {
//			System.out.println(birthday);
//		}
		//System.out.println(sex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(birthday);
		System.out.println(date);
		User user = (User)request.getSession().getAttribute("loginUser");
		user.setBirthday(date);
		user.setSex(sex);
		
		user.setEmail(email);
		user.setName(nickname);
		user.setTelephone(telephone);
		userService.updateUser(user);
		return "redirect:userinfo";
	}
}

