package com.store.service;

import java.util.List;

import com.store.entity.PageBean;
import com.store.entity.Product;


public interface ProductService {

	List<Product> findHots();

	List<Product> findNews();

	Product findProductByPid(String pid);

	PageBean<Product> findProductsByCidWithPage(String cid, int currPage, int pageSize);

//	PageBean<Product> findAllProductsWithPage(int currPage, int pageSize) throws Exception;
//
//	void saveProduct(Product product) throws Exception;
}
