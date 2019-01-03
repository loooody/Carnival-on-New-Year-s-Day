package com.store.service;

import java.sql.SQLException;
import java.util.List;

import com.store.entity.Category;

/**
 * 
 * 分类管理业务层接口
 * @author cb110
 *
 */

public interface CategoryService {

	/**
	 * 
	 * 查询所有分类
	 * @return
	 */
	List<Category> getAllCats();

	/**
	 * 
	 * 添加商品
	 * @param category
	 */
	void addCategory(Category category);
	
	/**
	 * 
	 * 修改商品
	 * @param cid
	 */
	void editCategory(Category category);
	
	/**
	 * 
	 * 查询商品类别
	 * @param cid
	 * @return
	 */
	Category getCategory(String cid);
	
	/**
	 * 
	 * 删除商品类别
	 * @param cid
	 */
	void delCategory(String cid);

}
