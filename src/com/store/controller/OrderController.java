package com.store.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Cart;
import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.impl.OrderServiceImpl;
import com.store.utils.UUIDUtils;



@Controller
@RequestMapping("Order")
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;
	
	@RequestMapping("saveOrder")
	public ModelAndView saveOrder(HttpServletRequest request) throws Exception{
		UUIDUtils u = new UUIDUtils();
		String orderItem = u.getUUID64();
		String oid = u.getCode();
		Product p = new Product("1",1399.0);
		Date date = new Date();
		User user = new User("DFE7C351CCCF4E8DBEB6E4C6079BFD35","aa");
		
		
//		Order o = new Order(oid,date,3398.0,1,"456","456","456",user);
		Order order = new Order();
		order.setOid(oid);
		order.setOrdertime(new Date());
		order.setTotal(3398.0);
		order.setState(1);
		order.setUser(user);
//		OrderItem orderitem = new OrderItem(orderItem,2,4567.0,p,o);
		OrderItem orderitem = new OrderItem();
		orderitem.setItemid(UUIDUtils.getCode());
		orderitem.setQuantity(2);
		orderitem.setTotal(123);
		orderitem.setProduct(p);
		// 设置当前的订单项属于哪个订单：从程序的角度体现订单项和订单的关系
		orderitem.setOrder(order);
		order.getList().add(orderitem);
		
		orderService.saveOrder(order);
		
		request.getSession().setAttribute("order", order);
		return new ModelAndView("jsp/order_info");
	}
}
