package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.PageBean;
import com.store.entity.Product;
import com.store.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productService;

	@RequestMapping("findProductByPid")
	public ModelAndView findProductByPid(String pid) {
		Product product = productService.findProductByPid(pid);
		return new ModelAndView("jsp/product_info","product",product);
	}
	
	@RequestMapping("findProductsByCidWithPage")
	public ModelAndView findProductsByCidWithPage(String cid,int currPage) {
		int pageSize = 12;//每页显示记录数
		PageBean<Product> pageBean = productService.findProductsByCidWithPage(cid, currPage, pageSize);
		return new ModelAndView("jsp/product_list","page",pageBean);
	}
}
