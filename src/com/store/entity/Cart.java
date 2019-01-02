package com.store.entity;

/**
 * 购物车实体类
 *
 */
public class Cart {

	private String cartid;//购物车编号
	private double total;//购物车总价
	private String uid;//用户编号
	
	
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", total=" + total + ", uid=" + uid + "]";
	}
	
	
}
	

