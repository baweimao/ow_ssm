package com.dongzhi.ow.service;

import java.util.List;

import com.dongzhi.ow.pojo.Foot;

public interface FootService {

	void update(Foot foot);
	Foot get(int id);
	List<Foot> list();
}
