package com.store.service;

import java.util.List;


import com.store.entity.Cart;
import com.store.entity.CartItem;
import com.store.entity.User;
/**
 * 购物车业务层接口
 * @author xiaoming
 *
 */
public interface CartService {
	
	/**
	 * 根据用户id查找购物车
	 * @param uid
	 * @return
	 */
	Cart findCartByUid(String uid);

	
	/**
	 * 添加购物项到购物车
	 * @param user	用户对象
	 * @param pid	商品编号
	 * @param quantity	商品数量
	 */
	void addCartItemToCart(User user,String pid,int quantity);
	
	
	/**
	 * 根据购物车编号查找购物项
	 * @param cartid
	 * @return
	 */
	List<CartItem> findCartItemByCartId(String cartid);
	
	
	/**
	 * 删除购物项
	 * @param pid  商品编号
	 * @param cart  购物车对象
	 */
	void deleteCartItem(String pid,Cart cart);
	
	/**
	 * 清空购物车
	 * @param cart
	 */
	void clearCart(Cart cart);
	
	/**
	 * 更新购物车信息
	 * @param cart  购物车对象
	 * @param pid  商品编号
	 * @param num 商品修改的数量（增加或减少用正数或负数表示）
	 * @return 返回字符串数据，用“@”分隔，数据包含 1.购物项小计 2.购物车总金额总金额
	 */
	String updateCart(Cart cart,String pid,int num);
	
}

