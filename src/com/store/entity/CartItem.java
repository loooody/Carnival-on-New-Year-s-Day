package com.store.entity;
/**
 * 购物项，表示购物车中的每一类商品
 * @author xiaoming
 *
 */
public class CartItem {
	
	private Product product;
	private int num;//当前类别的商品数量
	private double subTotal;//小计
	
	//小计经过计算可以获取到
	public double getSubTotal() {
		return product.getShop_price()*num;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
}
