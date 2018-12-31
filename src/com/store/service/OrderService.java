package com.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.entity.CartItem;
import com.store.entity.Order;
import com.store.entity.User;

@Service
public interface OrderService {

	void saveOrder(Order order) throws Exception;
	
	Order setValueForOrder(List<CartItem> list, User user);
//
//	PageBean<Order> findMyOrdersWithPage(User user, int currPage, int pageSize) throws Exception;
//
//	Order findOrderByOid(String oid) throws Exception;
//
//	void updateOrder(Order order) throws Exception;
//
//	List<Order> findAllOrders() throws Exception;
//
//	List<Order> findAllOrdersByState(int state) throws Exception;

}
