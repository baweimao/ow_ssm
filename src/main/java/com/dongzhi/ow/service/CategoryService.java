package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Category;

public interface CategoryService {

	void add(Category category);
	void delete(int id);
	void update(Category category);
	Category get(int id);
	List<Category> getOrder(int order);
	List<Category> list();
	List<Category> listInit();
}
