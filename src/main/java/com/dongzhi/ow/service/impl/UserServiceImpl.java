package com.dongzhi.ow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongzhi.ow.mapper.UserMapper;
import com.dongzhi.ow.pojo.User;
import com.dongzhi.ow.pojo.UserExample;
import com.dongzhi.ow.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void update(User u) {
		userMapper.updateByPrimaryKeySelective(u);	
	}
	
	@Override
	public User get(String name, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> result = userMapper.selectByExample(example);
		if(result.isEmpty())
			return null;
		return result.get(0);
	}
}
