package com.store.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 购物车
 * 2个属性3个方法
 * @author xiaoming
 *
 */


public class Cart {

	private double total = 0;//总计/积分
	//	个数不确定的购物项
	private Map<String , CartItem> map = new HashMap<String,CartItem>();
	
	/**
	 * 添加购物项到购物车
	 * 当用户点击加入购物车按钮,可以将当前要购买的商品id,商品数量发送到服务端,服务端根据商品id查询到商品信息
	 * 有了商品信息Product对象,有了要购买商品数量,当前的购物项也就可以获取到了
	 */
	public void addCartItemToCar(CartItem cartItem) {
		//获取到正在向购物车中添加的商品pid
		String pid = cartItem.getProduct().getPid();
		
		//将当前的购物项加入购物车之前,判断之前是否买过这类商品
		//如果没有买过    map.put()
		//如果买过: 获取到原先的数量,获取到本次的数量,相加之后设置到原先购物项上
		if(map.containsKey(pid)) {
			//获取到原先的购物项
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}
	
	/**
	 * 移除购物项
	 */
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		map.clear();
	}
	
	/**
	 * 返回map中所有的值
	 * 在页面中使用${empty cart.cartItems}会调用这个方法,用于判断map是否为空
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	//总计经过计算可以获得
	public double getTotal() {
		//先让总计请0
		total = 0;
		//获取到Map中所有的购物项
		Collection<CartItem> values = map.values();
		//遍历所有的购物项,将购物项上的小计相加
		for (CartItem cartItem : values) {
			total += cartItem.getSubTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	
	
}
