package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.NewsMapper;
import com.dongzhi.ow.pojo.News;
import com.dongzhi.ow.pojo.NewsExample;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.service.NewsService;
import com.dongzhi.ow.service.WebService;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsMapper newsMapper;
	@Autowired
	WebService webService;
	
	@Override
	public List<News> list(int up) {
		NewsExample example = new NewsExample();
		example.createCriteria().andUpEqualTo(up);
		example.setOrderByClause("newsDate desc");
		return newsMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<News> listInit() {
		NewsExample example = new NewsExample();
		example.createCriteria().andNewsOrderNotEqualTo(0);
		example.setOrderByClause("newsDate desc");
		return newsMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<News> listOrder(int up) {
		NewsExample example = new NewsExample();
		example.createCriteria().andUpEqualTo(up);
		example.setOrderByClause("newsOrder asc");
		return newsMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<News> listOrderInit(int up) {
		NewsExample example = new NewsExample();
		example.createCriteria().andNewsOrderNotEqualTo(0).andUpEqualTo(up);
		example.setOrderByClause("newsOrder asc");
		return newsMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void initNews(List<News> ns) {
		for(News n:ns) {
			int wid = n.getWid();
			Web w = webService.get(wid);
			n.setWeb(w);
		}
	}
	
	@Override
	public void add(News news) {
		newsMapper.insert(news);
	}
	
	@Override
	public void delete(int id) {
		newsMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public News get(int id) {
		return newsMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<News> getOrder(int up, int order) {
		NewsExample example = new NewsExample();
		example.createCriteria().andUpEqualTo(up).andNewsOrderEqualTo(order);
		return newsMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public void update(News news) {
		newsMapper.updateByPrimaryKeySelective(news);
	}
}
