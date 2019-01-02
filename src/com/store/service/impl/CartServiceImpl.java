package com.store.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CartDao;
import com.store.dao.CartItemDao;
import com.store.dao.ProductDao;
import com.store.entity.Cart;
import com.store.entity.CartItem;
import com.store.entity.Product;
import com.store.entity.User;
import com.store.service.CartService;
import com.store.utils.UUIDUtils;
/**
 * 购物车业务层实现类
 * @author xiaoming
 *
 */
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	
	@Override
	public Cart findCartByUid(String uid) {
		return cartDao.findCartByUid(uid);
	}
	
	@Override
	public List<CartItem> findCartItemByCartId(String cartid) {
		return cartItemDao.findCartItemByCartId(cartid);
	}

	

	@Override
	public void addCartItemToCart(User user, String pid, int quantity) {
		//获取当前用户的购物车
		Cart cart01 = cartDao.findCartByUid(user.getUid());
		
		//通过商品id获得商品对象
		Product product = productDao.findProductByPid(pid);
		
		if(cart01 == null) {
			//购物车为空
			Cart cart = new Cart();
			String cartid = UUIDUtils.getId();
			cart.setCartid(cartid);
			cart.setUid(user.getUid());
			cart.setTotal(product.getShop_price() * quantity);
			cartDao.addCart(cart);
			
			CartItem cartItem = new CartItem();
			cartItem.setItemid(UUIDUtils.getId());
			cartItem.setQuantity(quantity);
			cartItem.setTotal(quantity * product.getShop_price());
			cartItem.setProduct(product);
			cartItem.setCartid(cartid);
			cartItemDao.addCartItem(cartItem);
		}else {
			//购物车不为空
			//根据购物车id拿到对应购物项
			List<CartItem> list = cartItemDao.findCartItemByCartId(cart01.getCartid());
			//遍历购物项，从购物项中查找是否存在pid相同的，存在就增加quantity和total，不存在就新增购物项
			
			boolean flag = false;
			
			Iterator<CartItem> it = list.iterator();
			while(it.hasNext()) {
				CartItem cartitem = it.next();
				if(cartitem.getProduct().getPid().equals(pid)) {
					cartitem.setQuantity(cartitem.getQuantity() + quantity);
					cartitem.setTotal(cartitem.getTotal() + product.getShop_price()*quantity);
					cartItemDao.updateCartItem(cartitem);
					
					//修改购物车总金额total
					cart01.setTotal(cart01.getTotal() + product.getShop_price()*quantity);
					cartDao.updateCart(cart01);
					
					flag = true;
					break;
				}
			}
			
			if(flag == false) {
				CartItem cartItem2 = new CartItem();
				cartItem2.setItemid(UUIDUtils.getId());
				cartItem2.setQuantity(quantity);
				cartItem2.setTotal(quantity * product.getShop_price());
				cartItem2.setProduct(product);
				cartItem2.setCartid(cart01.getCartid());
				cartItemDao.addCartItem(cartItem2);
				
				//修改购物车总金额total
				cart01.setTotal(cart01.getTotal() + quantity * product.getShop_price());
				cartDao.updateCart(cart01);
			}
			
		}
		
	}

	@Override
	public void deleteCartItem(String pid, Cart cart) {
		//更新购物车总价
		List<CartItem> list = cartItemDao.findCartItemByCartId(cart.getCartid());
		
		double total = 0;
		for (CartItem item : list) {
			if(item.getProduct().getPid().equals(pid)) {
				total = item.getTotal();
				break;
			}
		}
		cart.setTotal(cart.getTotal() - total);
		
		if(list.size() == 1) {
			//如果购物车中只剩下一个购物项，删除购物项后，还需要删除购物车
			cartItemDao.deleteCartItem(pid, cart.getCartid());
			cartDao.deleteCart(cart);
		}else {
			cartDao.updateCart(cart);
			//删除购物项
			cartItemDao.deleteCartItem(pid, cart.getCartid());
		}
		
	}

	@Override
	public void clearCart(Cart cart) {
		//删除所有购物项
		cartItemDao.deleteAllCartItem(cart.getCartid());
		//删除购物车
		cartDao.deleteCart(cart);
	}

	@Override
	public String updateCart(Cart cart, String pid, int num) {
		//修改购物项数量，小计
		List<CartItem> list = cartItemDao.findCartItemByCartId(cart.getCartid());
		int quantity = 0;
		double subTotal = 0;
		double total = 0;
		Iterator<CartItem> it = list.iterator();
		while(it.hasNext()) {
			CartItem cartItem = it.next();
			if(cartItem.getProduct().getPid().equals(pid)) {
				//修改数量
				quantity = cartItem.getQuantity() + num;
				cartItem.setQuantity(quantity);
				//修改小计
				total = num*cartItem.getProduct().getShop_price();
				subTotal = cartItem.getTotal() + total;
				cartItem.setTotal(subTotal);
				cartItemDao.updateCartItem(cartItem);
				break;
			}
		}
		
		//修改购物车总金额
		total = cart.getTotal() + total;
		cart.setTotal(total);
		cartDao.updateCart(cart);
		
		//返回json格式的数据，包括购物项小计和购物车总金额
		//"{'subTotal':1,'total':10}"
		//String jsonStr = "{'subTotal':" + subTotal + ",'total':" + total + "}";
		//System.out.println(jsonStr);
		
		//返回包含 购物项小计和购物车总金额 的字符串，用@分隔
		String str = String.valueOf(subTotal) + "@" + String.valueOf(total);
		//System.out.println(str);
		return str;
	}

	


	
	
}
