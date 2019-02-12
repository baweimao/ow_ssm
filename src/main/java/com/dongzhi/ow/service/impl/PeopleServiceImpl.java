package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.PeopleMapper;
import com.dongzhi.ow.pojo.People;
import com.dongzhi.ow.pojo.PeopleExample;
import com.dongzhi.ow.pojo.WebExample;
import com.dongzhi.ow.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	PeopleMapper peopleMapper;
	
	@Override
	public List<People> list(int tid) {
		PeopleExample example = new PeopleExample();
		example.createCriteria().andTidEqualTo(tid);
		example.setOrderByClause("peopleOrder asc");
		return peopleMapper.selectByExample(example);
	}
	
	@Override
	public void add(People people) {
		peopleMapper.insert(people);
	}
	
	@Override
	public void delete(int id) {
		peopleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public People get(int id) {
		return peopleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<People> getOrder(int tid, int order) {
		PeopleExample example = new PeopleExample();
		example.createCriteria().andTidEqualTo(tid).andPeopleOrderEqualTo(order);
		return peopleMapper.selectByExample(example);
	}
	
	@Override
	public void update(People people) {
		peopleMapper.updateByPrimaryKeySelective(people);
	}
}
