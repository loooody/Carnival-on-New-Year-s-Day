package com.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.dao.OrderDao;
import com.store.entity.CartItem;
import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.User;
import com.store.service.OrderService;
import com.store.utils.UUIDUtils;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 保存订单
	 */
	@Override
	@Transactional
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.saveOrder(order);
		for(OrderItem item : order.getList())
			orderDao.saveOrderItem(item);
	}

	/**
	 * 为订单的对应字段赋值
	 */
	@Override
	public Order setValueForOrder(List<CartItem> list, User user) {
		// 创建订单对象，为订单对象赋值
		Order order = new Order();
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		// order.setTotal(list.size());
		order.setState(1);
		order.setUser(user);
		double totalPrice = 0;
		// 遍历购物项的同时，创建订单项，为订单项赋值
		for (CartItem item : list) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotal(item.getTotal());
			totalPrice += item.getTotal();
			orderItem.setProduct(item.getProduct());
			// 设置当前的订单项属于哪个订单：从程序的角度体现订单项和订单的关系
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		order.setTotal(totalPrice);

		return order;
	}

	

}

