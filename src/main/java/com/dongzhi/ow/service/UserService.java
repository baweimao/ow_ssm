package com.dongzhi.ow.service;

import com.dongzhi.ow.pojo.User;

public interface UserService {
	void update(User u) ;
	User get(String name, String password);
}
