package com.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.User;
/**
 * 权限拦截器
 *
 */
public class PrivilegeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断当前的session中是否存在已经登录成功的用户
		User user = (User)request.getSession().getAttribute("loginUser");
		if(user == null) {
			//System.out.println("请登录后再进行访问");
			request.setAttribute("msg", "请登录后再进行访问");
			request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
