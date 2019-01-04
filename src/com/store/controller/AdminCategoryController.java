package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.store.entity.Category;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.UUIDUtils;

/**
 * 
 * 分类管理控制层
 * @author cb110
 *
 */

@Controller
@RequestMapping("adminCategory")
public class AdminCategoryController {
	@Autowired
	CategoryServiceImpl categoryService;
	/**
	 * 
	 * 查询分类
	 * @param model
	 * @return
	 */
	@RequestMapping("getAllCategory")
	public ModelAndView findAllCats(Model model){
		List<Category> list=categoryService.getAllCats();
		model.addAttribute("allCats", list);
		return new ModelAndView("admin/category/list");
	}
	
	
	@RequestMapping("addCategoryUI")
	public ModelAndView addCategoryUI(){
		return new ModelAndView("admin/category/add");
	}
	
	@RequestMapping("editCategoryUI")
	public ModelAndView editCategoryUI(Model model,String cid){
		Category category =categoryService.getCategory(cid);
		model.addAttribute("category", category);
		return new ModelAndView("admin/category/edit");
	}
	
	/**
	 * 
	 * 添加分类
	 * @param model
	 * @param cname
	 * @return
	 */
	@RequestMapping("addCategory")
	public ModelAndView addCategory(Model model,String cname){
		Category category =new Category();
		category.setCid(UUIDUtils.getId());
		category.setCname(cname);
		categoryService.addCategory(category);
		return new ModelAndView("redirect:getAllCategory");
	}
	
	/**
	 * 
	 * 修改分类
	 * @param cid
	 * @return
	 */
	@RequestMapping("editCategory")
	public ModelAndView editCategory(String cid,String cname){
		Category category =new Category();
		category.setCid(cid);
		category.setCname(cname);
		categoryService.editCategory(category);
		return new ModelAndView("redirect:getAllCategory");
	}
	
	/**
	 * 
	 * 删除商品类别
	 * @param cid
	 * @return
	 */
	@RequestMapping("delCategory")
	public ModelAndView delCategory(String cid){
		categoryService.delCategory(cid);
		return new ModelAndView("redirect:getAllCategory");
	}
}