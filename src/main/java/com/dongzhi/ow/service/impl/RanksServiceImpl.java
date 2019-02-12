package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.RanksMapper;
import com.dongzhi.ow.pojo.Ranks;
import com.dongzhi.ow.pojo.RanksExample;
import com.dongzhi.ow.service.RanksService;

@Service
public class RanksServiceImpl implements RanksService{

	@Autowired
	RanksMapper ranksMapper;
	
	@Override
	public List<Ranks> list(int gid) {
		RanksExample example = new RanksExample();
		example.createCriteria().andGidEqualTo(gid);
		example.setOrderByClause("ranksOrder asc");
		return ranksMapper.selectByExample(example);
	}
	
	@Override
	public List<Ranks> listInit(int gid) {
		RanksExample example = new RanksExample();
		example.createCriteria().andRanksOrderNotEqualTo(0).andGidEqualTo(gid);
		example.setOrderByClause("ranksOrder asc");
		return ranksMapper.selectByExample(example);
	}
	
	@Override
	public void add(Ranks ranks) {
		ranksMapper.insert(ranks);
	}
	
	@Override
	public void delete(int id) {
		ranksMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Ranks get(int id) {
		return ranksMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Ranks> getOrder(int gid, int order) {
		RanksExample example = new RanksExample();
		example.createCriteria().andGidEqualTo(gid).andRanksOrderEqualTo(order);
		return ranksMapper.selectByExample(example);
	}
	
	@Override
	public void update(Ranks ranks) {
		ranksMapper.updateByPrimaryKeySelective(ranks);
	}
}
