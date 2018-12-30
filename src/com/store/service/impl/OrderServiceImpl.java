package com.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.dao.OrderDao;
import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	@Transactional
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.saveOrder(order);
		for(OrderItem item : order.getList())
			orderDao.saveOrderItem(item);
	}

	
	

}

