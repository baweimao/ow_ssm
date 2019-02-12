package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Type;

public interface TypeService {

	void add(Type type);
	void delete(int id);
	void update(Type type);
	Type get(int id);
	List<Type> getOrder(int order);
	List<Type> list();
	List<Type> listInit();
}
