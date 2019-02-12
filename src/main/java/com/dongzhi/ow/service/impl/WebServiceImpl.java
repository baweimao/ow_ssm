package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.WebMapper;
import com.dongzhi.ow.pojo.Web;
import com.dongzhi.ow.pojo.WebExample;
import com.dongzhi.ow.service.WebService;

@Service
public class WebServiceImpl implements WebService{

	@Autowired
	WebMapper webMapper;
	
	@Override
	public List<Web> list() {
		WebExample example = new WebExample();
		example.setOrderByClause("webOrder asc");
		return webMapper.selectByExample(example);
	}
	
	@Override
	public List<Web> list(int cid) {
		WebExample example = new WebExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("webOrder asc");
		return webMapper.selectByExample(example);
	}
	
	@Override
	public void add(Web web) {
		webMapper.insert(web);
	}
	
	@Override
	public void delete(int id) {
		webMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Web get(int id) {
		return webMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Web> getOrder(int cid, int order) {
		WebExample example = new WebExample();
		example.createCriteria().andCidEqualTo(cid).andWebOrderEqualTo(order);
		return webMapper.selectByExample(example);
	}
	
	@Override
	public void update(Web web) {
		webMapper.updateByPrimaryKeySelective(web);
	}
}
