package com.store.service;

import java.util.List;

import com.store.entity.PageBean;
import com.store.entity.Product;

/**
 * 
 * 商品管理业务层接口
 * @author cb110
 *
 */

public interface ProductService {

	/**
	 * 
	 * 查询最热商品
	 * @return
	 */
	List<Product> findHots();

	/**
	 * 
	 * 查询最新商品
	 * @return
	 */
	List<Product> findNews();

	/**
	 * 
	 * 通过商品id查询商品
	 * @param pid
	 * @return
	 */
	Product findProductByPid(String pid);

	/**
	 * 
	 * 通过商品类别分页查找商品
	 * @param cid
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Product> findProductsByCidWithPage(String cid, int currPage, int pageSize);

	/**
	 * 
	 * 分页查找商品
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Product> findAllProductsWithPage(int currPage, int pageSize);

	/**
	 * 
	 * 添加商品
	 * @param product
	 */
	void saveProduct(Product product);
	
	/**
	 * 
	 * 商品上、下架
	 * @param pflag
	 * @param pid
	 */
	void saletype(int pflag,String pid);
	
	/**
	 * 
	 * 分页查找上、下架商品
	 * @param pflag
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Product> findProductsByPflagWithPage(int pflag, int currPage, int pageSize);
	
	/**
	 * 
	 * 修改商品
	 * @param product
	 * @param pid
	 */
	void editProduct(Product product,String pid);
	
	/**
	 * 
	 * 删除商品
	 * @param pid
	 */
	void delProduct(String pid);
}
