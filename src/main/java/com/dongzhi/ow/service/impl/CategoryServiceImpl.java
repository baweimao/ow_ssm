package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.CategoryMapper;
import com.dongzhi.ow.pojo.Category;
import com.dongzhi.ow.pojo.CategoryExample;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.CategoryService;
import com.dongzhi.ow.service.WebService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	WebService webService;
	
	@Override
	public List<Category> list() {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("categoryOrder asc");
		return categoryMapper.selectByExample(example);
	}
	
	//首页显示用
	@Override
	public List<Category> listInit() {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andCategoryOrderNotEqualTo(0);
		example.setOrderByClause("categoryOrder asc");
		List<Category> cs = categoryMapper.selectByExample(example);
		for(Category c:cs) {
			int cid = c.getId();
			List<Web> ws = webService.list(cid);
			c.setWs(ws);
		}
		return cs;
	}
	
	@Override
	public void add(Category category) {
		categoryMapper.insert(category);
	}
	
	@Override
	public void delete(int id) {
		categoryMapper.deleteByPrimaryKey(id);	
	}
	
	@Override
	public Category get(int id) {
		return categoryMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Category> getOrder(int order) {
		CategoryExample example = new CategoryExample();
		example.createCriteria().andCategoryOrderEqualTo(order);
		return categoryMapper.selectByExample(example);
	}
	
	@Override
	public void update(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}
}
