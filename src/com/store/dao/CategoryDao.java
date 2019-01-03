package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.entity.Category;

/**
 * 
 * 分类管理dao接口
 * @author cb110
 *
 */

public interface CategoryDao {

	/**
	 * 
	 * 查询所有商品类别
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
	 * 修改商品类别
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

