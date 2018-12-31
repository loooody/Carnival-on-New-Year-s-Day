package com.store.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Cart;
import com.store.entity.CartItem;
import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.OrderService;
import com.store.service.impl.CartServiceImpl;
import com.store.service.impl.OrderServiceImpl;
import com.store.utils.UUIDUtils;




@Controller
@RequestMapping("Order")
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	CartServiceImpl cartService;
	
	@RequestMapping("saveOrder")
	public ModelAndView saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// 确认用户登录状态
		User user = (User) request.getSession().getAttribute("loginUser");
		if (null == user) {
			request.setAttribute("msg", "请登录之后再下单");
			return new ModelAndView("jsp/info.jsp");
		}
		// 获取购物车
		List<CartItem> list =  (List<CartItem>) request.getSession().getAttribute("cartItems");
//		System.out.println(list.isEmpty());
		Order order = orderService.setValueForOrder(list, user);
//		System.out.println(order.toString());
		// 调用业务层功能：保存订单
		orderService.saveOrder(order);
		
		// 清空购物车
		Cart cart = cartService.findCartByUid(user.getUid());
		cartService.clearCart(cart);
		
		// 将订单放入request
		request.setAttribute("order", order);
		return new ModelAndView("jsp/order_info");
	}
}
