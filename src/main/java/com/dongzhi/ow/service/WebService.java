package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Web;

public interface WebService {

	void add(Web web);
	void delete(int id);
	void update(Web web);
	Web get(int id);
	List<Web> getOrder(int cid, int order);
	List<Web> list(int cid);
	List<Web> list();
}
