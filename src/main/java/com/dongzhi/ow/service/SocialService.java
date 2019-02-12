package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Social;

public interface SocialService {
	
	void add(Social social);
	void delete(int id);
	void update(Social social);
	Social get(int id);
	List<Social> getOrder(int pid, int order);
	List<Social> list(int pid);
}
