package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.LiveMapper;
import com.dongzhi.ow.pojo.Live;
import com.dongzhi.ow.pojo.LiveExample;
import com.dongzhi.ow.service.LiveService;

@Service
public class LiveServiceImpl implements LiveService{

	@Autowired
	LiveMapper liveMapper;
	
	@Override
	public List<Live> list(int gid) {
		LiveExample example = new LiveExample();
		example.createCriteria().andGidEqualTo(gid);
		example.setOrderByClause("liveOrder asc");
		return liveMapper.selectByExample(example);
	}
	
	@Override
	public List<Live> listInit(int gid) {
		LiveExample example = new LiveExample();
		example.createCriteria().andGidEqualTo(gid);
		example.setOrderByClause("liveOrder desc");
		return liveMapper.selectByExample(example);
	}
	
	@Override
	public void add(Live live) {
		liveMapper.insert(live);
	}
	
	@Override
	public void delete(int id) {
		liveMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Live get(int id) {
		return liveMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Live> getOrder(int gid, int order) {
		LiveExample example = new LiveExample();
		example.createCriteria().andGidEqualTo(gid).andLiveOrderEqualTo(order);
		return liveMapper.selectByExample(example);
	}
	
	@Override
	public void update(Live live) {
		liveMapper.updateByPrimaryKeySelective(live);
	}
}
