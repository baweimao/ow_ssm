package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.SocialMapper;
import com.dongzhi.ow.pojo.Social;
import com.dongzhi.ow.pojo.SocialExample;
import com.dongzhi.ow.service.SocialService;

@Service
public class SocialServiceImpl implements SocialService{

	@Autowired
	SocialMapper socialMapper;
	
	@Override
	public List<Social> list(int pid) {
		SocialExample example = new SocialExample();
		example.createCriteria().andPidEqualTo(pid);
		example.setOrderByClause("socialOrder asc");
		return socialMapper.selectByExample(example);
	}
	
	@Override
	public void add(Social social) {
		socialMapper.insert(social);
	}
	
	@Override
	public void delete(int id) {
		socialMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Social get(int id) {
		return socialMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Social> getOrder(int pid, int order) {
		SocialExample example = new SocialExample();
		example.createCriteria().andPidEqualTo(pid).andSocialOrderEqualTo(order);
		return socialMapper.selectByExample(example);
	}
	
	@Override
	public void update(Social social) {
		socialMapper.updateByPrimaryKeySelective(social);
	}
}
