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
	
}

