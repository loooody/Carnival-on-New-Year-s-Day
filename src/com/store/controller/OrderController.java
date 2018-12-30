package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Cart;
import com.store.service.impl.OrderServiceImpl;



@Controller
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;
	
	public ModelAndView saveOrder(){

		
		return new ModelAndView("");
	}
}
