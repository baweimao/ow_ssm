package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.People;

public interface PeopleService {
	void add(People people);
	void delete(int id);
	void update(People people);
	People get(int id);
	List<People> getOrder(int tid, int order);
	List<People> list(int tid);
}
