package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@RequestMapping("indexUI")
	public ModelAndView getIndexUI() {
		
		return new ModelAndView("/jsp/index");
	}
}
