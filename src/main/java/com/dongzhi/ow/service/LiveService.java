package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Live;

public interface LiveService {

	void add(Live live);
	void delete(int id);
	void update(Live live);
	Live get(int id);
	List<Live> getOrder(int gid, int order);
	List<Live> list(int gid);
	List<Live> listInit(int gid);
}
