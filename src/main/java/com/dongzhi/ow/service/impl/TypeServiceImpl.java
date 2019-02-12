package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.TypeMapper;
import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.Social;
import com.dongzhi.ow.pojo.Type;
import com.dongzhi.ow.pojo.TypeExample;
import com.dongzhi.ow.service.PeopleService;
import com.dongzhi.ow.service.SocialService;
import com.dongzhi.ow.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	TypeMapper typeMapper;
	@Autowired
	PeopleService peopleService;
	@Autowired
	SocialService socialService;
	
	@Override
	public List<Type> list() {
		TypeExample example = new TypeExample();
		example.setOrderByClause("typeOrder asc");
		return typeMapper.selectByExample(example);
	}
	
	//首页显示用
	@Override
	public List<Type> listInit() {
		TypeExample example = new TypeExample();
		example.createCriteria().andTypeOrderNotEqualTo(0);
		example.setOrderByClause("typeOrder asc");
		List<Type> ts = typeMapper.selectByExample(example);
		for(Type t:ts) {
			int tid = t.getId();
			List<People> ps = peopleService.list(tid);
			for(People p:ps) {
				int pid = p.getId();
				List<Social> ss = socialService.list(pid);
				p.setSs(ss);
			}
			t.setPs(ps);
		}
		return ts;
	}
	
	@Override
	public void add(Type type) {
		typeMapper.insert(type);
	}
	
	@Override
	public void delete(int id) {
		typeMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Type get(int id) {
		return typeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Type> getOrder(int order) {
		TypeExample example = new TypeExample();
		example.createCriteria().andTypeOrderEqualTo(order);
		return typeMapper.selectByExample(example);
	}
	
	@Override
	public void update(Type type) {
		typeMapper.updateByPrimaryKeySelective(type);
	}
}
