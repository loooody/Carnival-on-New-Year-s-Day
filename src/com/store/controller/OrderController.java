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
import com.store.entity.PageBean;
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
		System.out.println(list.isEmpty());
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
	
	/**
	 * 查看订单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("order_list")
	public ModelAndView findMyOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取用户信息
		User user = (User) request.getSession().getAttribute("loginUser");
//		System.out.println(user.toString());
		// 获取当前页
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 3;
		// 调用业务层：查询当前用户的订单信息，返回pageBean
		PageBean<Order> pageBean = orderService.findMyOrdersWithPage(user, currPage, pageSize);
		// 将PageBean放入request
		request.setAttribute("page", pageBean);
		// 转发
		return new ModelAndView("jsp/order_list");
	}
	
	/**
	 * 点击付款时根据订单ID来查询订单下的商品信息
	 * @param oid	//订单ID
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findOrderByOid")
	public ModelAndView findOrderByOid(String oid,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Order order = orderService.getOrderByOid(oid);
		// 将订单放入request
		request.setAttribute("order", order);
		
		return new ModelAndView("jsp/order_info");
	}

}
