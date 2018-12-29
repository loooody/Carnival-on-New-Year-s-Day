package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.store.entity.Category;
import com.store.service.impl.CategoryServiceImpl;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryService;

	@RequestMapping(value="findAllCats",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findAllCats() {
		List<Category> list = categoryService.getAllCats();
		// 将全部分类转换为JSON格式的数据
		String jsonStr = JSONArray.fromObject(list).toString();
		//System.out.println(jsonStr);
		return jsonStr;
	}
}
