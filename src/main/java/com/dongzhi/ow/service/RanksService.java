package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Ranks;

public interface RanksService {

	void add(Ranks ranks);
	void delete(int id);
	void update(Ranks ranks);
	Ranks get(int id);
	List<Ranks> getOrder(int gid, int order);
	List<Ranks> list(int gid);
	List<Ranks> listInit(int gid);
}
