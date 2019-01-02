package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.entity.CartItem;

public interface CartItemDao {

	/**
	 * 新增购物项
	 * @param cartItem
	 */
	void addCartItem(CartItem cartItem);
	
	/**
	 * 根据购物车编号查找购物项
	 * @param cartid
	 * @return
	 */
	List<CartItem> findCartItemByCartId(String cartid);
	
	
	/**
	 * 更新购物项
	 * @param cartItem
	 */
	void updateCartItem(CartItem cartItem);
	
	/**
	 * 删除购物项
	 * @param pid  商品编号
	 * @param cartid  购物车编号
	 */
	void deleteCartItem(@Param("pid") String pid,@Param("cartid") String cartid);
	
	/**
	 * 删除所有购物项
	 * @param cartid
	 */
	void deleteAllCartItem(String cartid);
}

