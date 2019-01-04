package com.store.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.dao.OrderDao;
import com.store.entity.CartItem;
import com.store.entity.Order;
import com.store.entity.OrderItem;
import com.store.entity.PageBean;
import com.store.entity.Product;
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

	@Override
	public PageBean<Order> findMyOrdersWithPage(User user, int currPage, int pageSize) throws Exception {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrPage(currPage);// 当前页
		pageBean.setPageSize(pageSize);// 每页显示记录数
		// 查询当前分类下商品总个数 select * from orders where cid = ?
		int totalRecords = orderDao.getTotalRecords(user);
		
		pageBean.setTotalRecords(totalRecords);// 总记录数
		int totalPage = totalRecords % pageSize == 0 ? (totalRecords / pageSize) : (totalRecords / pageSize + 1);
		pageBean.setTotalPage(totalPage);// 总页数
		// 起始索引，select * from orders where uid=? limit ?,?
		int begin = (currPage - 1) * pageSize;
		List<Order> list = getOrderList(user, begin, pageSize);
		pageBean.setList(list);

		int startPage = currPage - 4; // 5
		int endPage = currPage + 4; // 13
		// 看看总页数够不够9页
		if (totalPage > 9) {
			// 超过了9页
			if (startPage < 1) {
				startPage = 1;
				endPage = startPage + 8;
			}
			if (endPage > totalPage) {
				endPage = totalPage;
				startPage = endPage - 8;
			}
		} else {
			// 不够9页
			startPage = 1;
			endPage = totalPage;
		}

		pageBean.setStartPage(startPage);
		pageBean.setEndPage(endPage);

		pageBean.setUrl("Order/order_list");

		return pageBean;
		
	}
	
	
	@Override
	/**
	 * 多表查询
	 */
	public List<Order> getOrderList(User user, int begin, int pageSize) throws Exception {
		List<Order> list = orderDao.findMyOrdersWithPage(user, begin, pageSize);
		
		//遍历所有订单
		for (Order order : list) {
			//获取到每笔订单oid 查询每笔订单下的订单项以及订单项对应商品的信息
			String oid = order.getOid();
			
			List<Map<String, Object>> list02 = orderDao.findOrderByOid(oid);
			//遍历list
			for (Map<String,Object> map : list02) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				
				// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
				// 1_创建时间类型的转换器
				DateConverter dt = new DateConverter();
				// 2_设置转换的格式
				dt.setPattern("yyyy-MM-dd");
				// 3_注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
				
				//将map中属于orderItem的数据自动填充到orderItem对象上
				BeanUtils.populate(orderItem, map);
				//将map中属于product的数据自动填充到orderItem对象上
				BeanUtils.populate(product, map);
				
				//让每个订单项和商品发生关联关系
				orderItem.setProduct(product);
				//将每个订单项存入订单下的集合中
				order.getList().add(orderItem);
			}
		}
		
		return list;
	}
	
	public Order getOrderByOid(String oid) throws Exception {
		Order order = orderDao.getOrder(oid);

		List<Map<String, Object>> list02 = orderDao.findOrderByOid(oid);
		// 遍历list
		for (Map<String, Object> map : list02) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();

			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);

			// 将map中属于orderItem的数据自动填充到orderItem对象上
			BeanUtils.populate(orderItem, map);
			// 将map中属于product的数据自动填充到orderItem对象上
			BeanUtils.populate(product, map);

			// 让每个订单项和商品发生关联关系
			orderItem.setProduct(product);
			// 将每个订单项存入订单下的集合中
			order.getList().add(orderItem);

		}

		return order;
	}

	/**
	 * 更新提交后的订单详情
	 * @param order
	 */
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(order);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrdersByState(int state) {
		return orderDao.findAllOrdersByState(state);
	}

	

}

