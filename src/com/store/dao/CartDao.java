package com.store.dao;

import com.store.entity.Cart;
/**
 * 购物车接口
 * @author xiaoming
 *
 */
public interface CartDao {

	/**
	 * 根据用户id查找购物车
	 * @param uid
	 * @return
	 */
	Cart findCartByUid(String uid);
	
	/**
	 * 新增购物车
	 * @param cart
	 */
	void addCart(Cart cart);
	
	/**
	 * 更新购物车
	 * @param cart
	 */
	void updateCart(Cart cart);
	
	/**
	 * 删除购物车
	 * @param cart
	 */
	void deleteCart(Cart cart);
}
