package com.store.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.UserDao;
import com.store.entity.User;
import com.store.service.UserService;
import com.store.utils.MailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User userLogin(User user) {
		// select * from user where username=? and password=?
		User us = userDao.userLogin(user);
		// 账号是否存在 使用ajax异步校验，
		// 这里判断登录失败的两种情况，1-密码错误，2-未激活
		if (us == null) {
			throw new RuntimeException("密码错误！");
		} else if (us.getState() == 0) {
			throw new RuntimeException("用户未激活");
		} else {
			return us;
		}
	}
	@Override
	public void userRegist(User user) {
		// TODO Auto-generated method stub
		 userDao.userRegist(user);
		 String emailMsg="欢迎您注册成为我们的会员,<a href='http://localhost:8080/StoreDemo/user?method=activa&code="+user.getCode()+"'>点击链接激活</a>";
	       try {
			MailUtils.sendMail(user,user.getEmail(), emailMsg);
				
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
	}

	@Override
	public boolean userActive(String code) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

