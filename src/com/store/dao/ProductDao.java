package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.entity.Product;

public interface ProductDao {

	List<Product> findHots();

	List<Product> findNews();

	Product findProductByPid(String pid);

	int findTotalRecords(String cid);

	List<Product> findProductsByCidWithPage(@Param("cid") String cid, @Param("begin") int begin, @Param("pageSize") int pageSize);

//	int findTotalRecords();
//
//	List<Product> findAllProductsWithPage(int begin, int pageSize);
//
//	void saveProduct(Product product);
}
