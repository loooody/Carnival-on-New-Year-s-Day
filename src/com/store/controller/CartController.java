package com.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Cart;
import com.store.entity.CartItem;
import com.store.entity.User;
import com.store.service.impl.CartServiceImpl;
import com.store.service.impl.ProductServiceImpl;

/**
 * 购物车控制层
 * @author xiaoming
 *
 */
@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartServiceImpl cartService;
	
	@Autowired
	ProductServiceImpl productService;
	
	/**
	 * 添加购物项到购物车
	 * @param request
	 * @param pid
	 * @param quantity
	 * @return
	 */
	@RequestMapping("addCartItemToCart")
	public ModelAndView addCartItemToCart(HttpServletRequest request,String pid,int quantity,Model model) {
		//获取到当前用户
		User user = (User) request.getSession().getAttribute("loginUser");
		
		cartService.addCartItemToCart(user,pid,quantity);
		
		findCartInfo(user,model,request);
		
		return new ModelAndView("jsp/cart");
	}
	
	/**
	 * 查询购物车信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("findCart")
	public ModelAndView findCart(HttpServletRequest request,Model model) {
		User user = (User) request.getSession().getAttribute("loginUser");
		
		findCartInfo(user,model,request);
		
		return new ModelAndView("jsp/cart");
	}
	
	/**
	 * 删除购物项
	 * @param request
	 * @param model
	 * @param pid
	 * @return
	 */
	@RequestMapping("removeCartItem")
	public ModelAndView removeCartItem(HttpServletRequest request,String pid) {
		User user = (User) request.getSession().getAttribute("loginUser");
		Cart cart = cartService.findCartByUid(user.getUid());
		
		cartService.deleteCartItem(pid, cart);
		
		return new ModelAndView("redirect:findCart");
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @return
	 */
	@RequestMapping("clearCart")
	public ModelAndView clearCart(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		Cart cart = cartService.findCartByUid(user.getUid());
		
		cartService.clearCart(cart);
		
		return new ModelAndView("redirect:findCart");
	}
	
	
	/**
	 * 更新购物车
	 * @param request
	 * @param pid	商品id
	 * @param num	修改的数量
	 * @return
	 */
	@RequestMapping(value="updateCart",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateCart(HttpServletRequest request,String pid,int num) {
		User user = (User) request.getSession().getAttribute("loginUser");
		Cart cart = cartService.findCartByUid(user.getUid());
		
		String str = cartService.updateCart(cart,pid,num);
		
		return str;
	}
	
	
	/**
	 * 公共方法，获取购物车信息并存入model中
	 * @param user
	 * @param model
	 */
	public void findCartInfo(User user,Model model,HttpServletRequest request) {
		Cart cart = cartService.findCartByUid(user.getUid());
		model.addAttribute("cart", cart);
		
		if(cart != null) {
			List<CartItem> list = cartService.findCartItemByCartId(cart.getCartid());
			model.addAttribute("cartItems", list);
			request.getSession().setAttribute("cartItems", list);
		}
	}
}

