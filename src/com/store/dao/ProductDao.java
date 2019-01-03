package com.store.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.entity.Product;
/**
 * 
 * 商品管理dao接口
 * @author cb110
 *
 */
public interface ProductDao {

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
	 * 通过商品类别查询商品
	 * @param cid
	 * @return
	 */
	int findTotalRecords(String cid);

	/**
	 * 
	 * 通过商品类别分页查找商品
	 * @param cid
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Product> findProductsByCidWithPage(@Param("cid") String cid, @Param("begin") int begin, @Param("pageSize") int pageSize);

	/**
	 * 
	 * 查询所有商品数量
	 * @return
	 */
	int findCount();

	/**
	 * 
	 * 分页查找所有商品
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Product> findAllProductsWithPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

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
	void saletype(@Param("pflag") int pflag,@Param("pid") String pid);
	
	/**
	 * 
	 * 分页查找上、下架商品
	 * @param pflag
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<Product> findProductsByPflagWithPage(@Param("pflag") int pflag,@Param("begin") int begin, @Param("pageSize") int pageSize);
	
	/**
	 * 
	 * 查询上、下架商品数量
	 * @param pflag
	 * @return
	 */
	int findTotal(int pflag);
	
	/**
	 * 
	 * 修改商品
	 * @param pname
	 * @param pflag
	 * @param market_price
	 * @param shop_price
	 * @param pimage
	 * @param cid
	 * @param pdesc
	 * @param pid
	 */
	void editProduct(@Param("pname") String pname, @Param("pflag") int pflag, @Param("market_price") double market_price, @Param("shop_price") double shop_price, @Param("pimage") String pimage, @Param("cid") String cid, @Param("pdesc")String pdesc, @Param("pid") String pid);
	
	/**
	 * 
	 * 删除商品
	 * @param pid
	 */
	void delProduct(String pid);
}

