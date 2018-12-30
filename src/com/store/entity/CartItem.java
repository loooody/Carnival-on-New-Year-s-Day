package com.store.entity;

import org.springframework.stereotype.Repository;

/**
 * 购物项实体类
 *
 */
@Repository
public class CartItem {
	private String itemid;//购物项编号
	private int quantity;//商品数量
	private double total;//小计
	private String cartid;//购物车编号
	
	//private String pid;//商品编号
	
	private Product product;//商品对象

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCartid() {
		return cartid;
	}

	public void setCartid(String cartid) {
		this.cartid = cartid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
}
