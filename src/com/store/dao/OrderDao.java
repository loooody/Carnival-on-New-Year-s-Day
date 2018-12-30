package com.store.dao;

import java.sql.Connection;
import java.util.List;

import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.User;


public interface OrderDao {

	void saveOrder(Order order);
	
	List<Order> findOrderByUid(String uid);
	
	List<Order> findOrderByOid(String oid);

	void saveOrderItem(OrderItem item);
//
//	int getTotalRecords(User user);
//
//	List<Order> findMyOrdersWithPage(User user, int begin, int pageSize);
//
//	Order findOrderByOid(String oid);
//
//	void updateOrder(Order order);
//
//	List<Order> findAllOrders();
//
//	List<Order> findAllOrdersByState(int state);

}
