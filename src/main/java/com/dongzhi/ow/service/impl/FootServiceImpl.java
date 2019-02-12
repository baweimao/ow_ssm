package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.FootMapper;
import com.dongzhi.ow.pojo.Foot;
import com.dongzhi.ow.pojo.FootExample;
import com.dongzhi.ow.service.FootService;

@Service
public class FootServiceImpl implements FootService{

	@Autowired
	FootMapper footMapper;
	
	@Override
	public Foot get(int id) {
		return footMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(Foot foot) {
		footMapper.updateByPrimaryKeySelective(foot);
	}
	
	@Override
	public List<Foot> list() {
		FootExample example = new FootExample();
		example.setOrderByClause("id asc");
		return footMapper.selectByExampleWithBLOBs(example);
	}
}
