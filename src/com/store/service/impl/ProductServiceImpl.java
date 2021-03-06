package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.ProductDao;
import com.store.entity.PageBean;
import com.store.entity.Product;
import com.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> findHots() {
		return productDao.findHots();
	}

	@Override
	public List<Product> findNews() {
		return productDao.findNews();
	}

	@Override
	public Product findProductByPid(String pid) {
		return productDao.findProductByPid(pid);
	}

	@Override
	public PageBean<Product> findProductsByCidWithPage(String cid, int currPage, int pageSize) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrPage(currPage);//当前页
		pageBean.setPageSize(pageSize);//每页显示记录数
		//	查询当前分类下商品总个数 select * from product where cid = ?
		int totalRecords = productDao.findTotalRecords(cid);
		pageBean.setTotalRecords(totalRecords);//总记录数
		int totalPage = totalRecords/pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		pageBean.setTotalPage(totalPage);//总页数
		//	起始索引，select * from product where cid=? limit ?,?
		int begin = (currPage-1)*pageSize;
		List<Product> list = productDao.findProductsByCidWithPage(cid,begin,pageSize);
		pageBean.setList(list);
		
		int startPage = currPage - 4; //5
		int endPage = currPage + 4;  //13
		//看看总页数够不够9页
		if(totalPage>9){
			//超过了9页
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPage){
				endPage = totalPage;
				startPage = endPage-8;
			}
		}else{
			//不够9页
			startPage = 1;
			endPage = totalPage;
		}
		
		pageBean.setStartPage(startPage);
		pageBean.setEndPage(endPage);
		
		pageBean.setUrl("product/findProductsByCidWithPage?cid="+cid);
		
		return pageBean;// TODO Auto-generated method stub
	}

	@Override
	public PageBean<Product> findAllProductsWithPage(int currPage, int pageSize) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrPage(currPage);//当前页
		pageBean.setPageSize(pageSize);//每页显示记录数
		//	查询当前分类下商品总个数 select * from product
		int totalRecords = productDao.findCount();
		pageBean.setTotalRecords(totalRecords);//总记录数
		int totalPage = totalRecords/pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		pageBean.setTotalPage(totalPage);//总页数
		//	起始索引，select * from product limit ?,?
		int begin = (currPage-1)*pageSize;
		List<Product> list = productDao.findAllProductsWithPage(begin,pageSize);
		pageBean.setList(list);
		
		int startPage = currPage - 4; //5
		int endPage = currPage + 4;  //13
		//看看总页数够不够9页
		if(totalPage>9){
			//超过了9页
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPage){
				endPage = totalPage;
				startPage = endPage-8;
			}
		}else{
			//不够9页
			startPage = 1;
			endPage = totalPage;
		}
		
		pageBean.setStartPage(startPage);
		pageBean.setEndPage(endPage);
		
		pageBean.setUrl("adminProduct/findAllProductsWithPage");
		
		return pageBean;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.saveProduct(product);
	}

	@Override
	public void saletype(int pflag, String pid) {
		// TODO Auto-generated method stub
		productDao.saletype(pflag, pid);
	}

	@Override
	public PageBean<Product> findProductsByPflagWithPage(int pflag, int currPage, int pageSize) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrPage(currPage);//当前页
		pageBean.setPageSize(pageSize);//每页显示记录数
		//	查询当前分类下商品总个数 select * from product
		
		
		int totalRecords = productDao.findTotal(pflag);
		
		
		pageBean.setTotalRecords(totalRecords);//总记录数
		int totalPage = totalRecords/pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		pageBean.setTotalPage(totalPage);//总页数
		//	起始索引，select * from product limit ?,?
		int begin = (currPage-1)*pageSize;
		
		
		List<Product> list = productDao.findProductsByPflagWithPage(pflag, begin, pageSize);
		
		
		pageBean.setList(list);
		
		int startPage = currPage - 4; //5
		int endPage = currPage + 4;  //13
		//看看总页数够不够9页
		if(totalPage>9){
			//超过了9页
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPage){
				endPage = totalPage;
				startPage = endPage-8;
			}
		}else{
			//不够9页
			startPage = 1;
			endPage = totalPage;
		}
		
		pageBean.setStartPage(startPage);
		pageBean.setEndPage(endPage);
		
		if(pflag==0){
			pageBean.setUrl("adminProduct/findAllOnsaleProductsWithPage");
		}else if(pflag==1){
			pageBean.setUrl("adminProduct/findAllUnsaleProductsWithPage");
		}
		
		return pageBean;
	}

	@Override
	public void editProduct(Product product,String pid) {
		// TODO Auto-generated method stub
		productDao.editProduct(product.getPname(), product.getPflag(), product.getMarket_price(), product.getShop_price(), product.getPimage(), product.getCid(), product.getPdesc(), pid);
	}

	@Override
	public void delProduct(String pid) {
		// TODO Auto-generated method stub
		productDao.delProduct(pid);
	}

}

