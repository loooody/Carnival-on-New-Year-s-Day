package com.store.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Order;
import com.store.service.impl.OrderServiceImpl;

import net.sf.json.JSONArray;

/**
 * 后台订单管理控制层
 *
 */
@Controller
@RequestMapping("adminOrder")
public class AdminOrderController {
	
	@Autowired
	OrderServiceImpl orderService;
	

	/**
	 * 查询所有订单
	 * @param request
	 * @param state
	 * @return
	 */
	@RequestMapping("findAllOrders")
	public ModelAndView findAllOrders(HttpServletRequest request,String state) {
		List<Order> list = null ;
		if(state==null || "".equals(state)) {
			list = orderService.findAllOrders();
		}else {
			int st = Integer.parseInt(state);
			list = orderService.findAllOrdersByState(st);
		}
		return new ModelAndView("admin/order/list","allOrders",list);
	}
	
	/**
	 * 根据订单编号查询订单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="findOrderByOidWithAjax",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findOrderByOidWithAjax(String id) throws Exception{
		//	查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
		Order order = orderService.getOrderByOid(id);
		System.out.println("id" + id);
		System.out.println("order" + order.toString());
		//	将返回的集合转换为JSON格式字符串,响应到客户端
		JSONArray fromObject = JSONArray.fromObject(order.getList());
		String jsonStr = fromObject.toString();
		System.out.println("json" + jsonStr);
		return jsonStr;
	}
	
	/**
	 * 修改订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateOrderByOid")
	public ModelAndView updateOrderByOid(String oid) throws Exception {
		//	根据OID查询这个订单
		Order order = orderService.getOrderByOid(oid);
		//	修改订单状态
		order.setState(3);
		orderService.updateOrder(order);
		//	重定向
		return new ModelAndView("redirect:findAllOrders?state=3");
	}
}
