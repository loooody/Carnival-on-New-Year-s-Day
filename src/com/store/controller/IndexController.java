package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Product;
import com.store.service.impl.ProductServiceImpl;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@Autowired
	ProductServiceImpl productService;
	
	@RequestMapping("indexUI")
	public ModelAndView getIndexUI(Model model) {
		List<Product> list1 = productService.findHots();
		List<Product> list2 = productService.findNews();
		
		model.addAttribute("hots", list1);
		model.addAttribute("news", list2);
		
		return new ModelAndView("/jsp/index");
	}
	
	
	@RequestMapping("search")
	public ModelAndView findResult(){
		
		
		return new ModelAndView("jsp/find_result");
	}
}
