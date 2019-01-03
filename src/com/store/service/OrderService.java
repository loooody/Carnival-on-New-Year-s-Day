package com.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.entity.CartItem;
import com.store.entity.Order;
import com.store.entity.PageBean;
import com.store.entity.User;

@Service
public interface OrderService {

	void saveOrder(Order order) throws Exception;
	
	Order setValueForOrder(List<CartItem> list, User user);

	PageBean<Order> findMyOrdersWithPage(User user, int currPage, int pageSize) throws Exception;
	
	List<Order> getOrderList(User user, int begin, int pageSize) throws Exception;
	
	public Order getOrderByOid(String oid) throws Exception;
	
//	Order findOrderByOid(String oid);

	void updateOrder(Order order) throws Exception;

	List<Order> findAllOrders();

	List<Order> findAllOrdersByState(int state);
	

}
