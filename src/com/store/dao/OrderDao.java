package com.store.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.User;

@Component
public interface OrderDao {

	void saveOrder(Order order);
	
	List<Map<String, Object>> findOrderByOid(String oid);

	void saveOrderItem(OrderItem item);

	int getTotalRecords(User user);

	List<Order> findMyOrdersWithPage(@Param("u")User user, @Param("b")int begin, @Param("p")int pageSize);

	void updateOrder(Order order);

	List<Order> findAllOrders();

	List<Order> findAllOrdersByState(@Param("state") int state);

	Order getOrder(String oid);

}
