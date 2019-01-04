package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.dao.CategoryDao;
import com.store.entity.Category;
import com.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getAllCats() {
		return categoryDao.getAllCats();
	}
	
	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	@Override
	public void editCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.editCategory(category);
	}

	@Override
	public Category getCategory(String cid) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(cid);
	}

	@Override
	public void delCategory(String cid) {
		// TODO Auto-generated method stub
		categoryDao.delCategory(cid);
}
}

